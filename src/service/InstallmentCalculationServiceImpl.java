package service;

import model.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class InstallmentCalculationServiceImpl implements InstallmentCalculationService {

    // Metoda zlicza wielkość raty na podstawie danych podanych z klasy InputData

    private final TimePointService timePointService;

    private final AmountsCalculationService amountsCalculationService;

    private final OverpaymentCalculatorService overpaymentCalculatorService;

    private final ReferenceCalculatorService referenceCalculatorService;

    private final ResidualCalculationService residualCalculationService;

    public InstallmentCalculationServiceImpl(
            TimePointService timePointService,
            AmountsCalculationService amountsCalculationService,
            OverpaymentCalculatorService overpaymentCalculatorService,
            ReferenceCalculatorService referenceCalculatorService,
            ResidualCalculationService residualCalculationService
            ) {
        this.timePointService = timePointService;
        this.amountsCalculationService = amountsCalculationService;
        this.residualCalculationService = residualCalculationService;
        this.overpaymentCalculatorService = overpaymentCalculatorService;
        this.referenceCalculatorService = referenceCalculatorService;
    }

    @Override
    public List<Installment> calculate(InputData inputData) {
        // Lista przechowująca raty
        List<Installment> installments = new LinkedList<>();

        // Rata ZEROWA
        BigDecimal installmentNumber = BigDecimal.ONE;

        Installment firstInstallment = calculateInstallment(installmentNumber, inputData);

        installments.add(firstInstallment);

        Installment previousInstallment = firstInstallment;

    for (
            BigDecimal index = installmentNumber.add(BigDecimal.ONE);
            index.compareTo(inputData.getMonthDuration()) <= 0;
            index = index.add(BigDecimal.ONE)
    ) {
      Installment nextInstallment = calculateInstallment(index, inputData, previousInstallment);
      installments.add(nextInstallment);
      previousInstallment = nextInstallment;
    }

        return installments;
    }

    // Liczy pierwszą ratę kredytu jako podstawa do obliczeń kolejnych rat, bez odniesienia do poprzednich rat
    private Installment calculateInstallment(BigDecimal installmentNumber, InputData inputData) {
        TimePoint timePoint = timePointService.calculate(installmentNumber, inputData);
        Overpayment overpayment = overpaymentCalculatorService.calculate(installmentNumber, inputData);
        InstallmentAmounts installmentAmount = amountsCalculationService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(installmentAmount, inputData);
        MortgageReference mortgageReference = referenceCalculatorService.calculate();

        return new Installment(timePoint, installmentNumber, installmentAmount, mortgageResidual, mortgageReference);
    }

    // Liczy każdą kolejną ratę kredytu
    private Installment calculateInstallment (BigDecimal installmentNumber, InputData inputData, Installment previousInstallment) {
        TimePoint timePoint = timePointService.calculate(installmentNumber, inputData);
        Overpayment  overpayment = overpaymentCalculatorService.calculate(installmentNumber, inputData);
        InstallmentAmounts installmentAmount = amountsCalculationService.calculate(inputData, overpayment, previousInstallment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(installmentAmount, previousInstallment);
        MortgageReference mortgageReference = referenceCalculatorService.calculate();

        return new Installment(timePoint, installmentNumber, installmentAmount, mortgageResidual, mortgageReference);
    }
}
