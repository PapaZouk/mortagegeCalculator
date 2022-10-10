package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.Overpayment;
import model.exception.InstallmentCalculationException;

import java.math.BigDecimal;

public class AmountCalculationServiceImpl implements AmountsCalculationService {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    private final ConstantAmountsCalculationService constantAmountsCalculationService;

    private final  DecreasingAmountsCalculationService decreasingAmountsCalculationService;

    public AmountCalculationServiceImpl(
            ConstantAmountsCalculationService constantAmountsCalculationService,
            DecreasingAmountsCalculationService decreasingAmountsCalculationService
    ) {
        this.constantAmountsCalculationService = constantAmountsCalculationService;
        this.decreasingAmountsCalculationService = decreasingAmountsCalculationService;
    }

    // Liczy wielkość raty na początku jej spłacania. Metoda
    // wspiera dwa scenariusze:
    // - kredyt o ratach stałych,
    // - kredyt o ratach malejących
    @Override
    public InstallmentAmounts  calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getInstallmentType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData, overpayment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData, overpayment);
            default:
                throw new InstallmentCalculationException();
        }
    }

    // Liczy wielkość raty z uwzględnieniem poprzedniej raty
    @Override
    public InstallmentAmounts calculate(InputData inputData, Overpayment overpayment, Installment previousInstallment) {
        switch (inputData.getInstallmentType()) {
            case CONSTANT:
                return constantAmountsCalculationService.calculate(inputData, overpayment, previousInstallment);
            case DECREASING:
                return decreasingAmountsCalculationService.calculate(inputData, overpayment, previousInstallment);
            default:
                throw new InstallmentCalculationException();
        }
    }

}
