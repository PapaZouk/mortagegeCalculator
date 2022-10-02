package service;

import model.InputData;
import model.Installment;

import java.util.List;

public class MortgageCalculationServiceImpl implements MortgageCalculationService {

    private final InstallmentCalculationService installmentCalculationService;
    private final PrintingService printingService;

    public MortgageCalculationServiceImpl(
            InstallmentCalculationService installmentCalculationService,
            PrintingService printingService)
    {
        this.installmentCalculationService = installmentCalculationService;
        this.printingService = printingService;
    }

    // Metoda do obliczeń rat kredytu. Drukuje informacje o kredycie, listę rat wraz z danymi o ratach
    @Override
    public void calculate(InputData inputData) {
        printingService.printInputDataInfo(inputData);

        List<Installment> installments = installmentCalculationService.calculate(inputData);
    }
}
