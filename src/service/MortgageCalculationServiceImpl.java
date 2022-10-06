package service;

import model.InputData;
import model.Installment;
import model.Summary;

import java.util.List;

public class MortgageCalculationServiceImpl implements MortgageCalculationService {

    private final InstallmentCalculationService installmentCalculationService;
    private final PrintingService printingService;

    private final SummaryService summaryService;

    public MortgageCalculationServiceImpl(
            InstallmentCalculationService installmentCalculationService,
            PrintingService printingService,
            SummaryService summaryService)
    {
        this.installmentCalculationService = installmentCalculationService;
        this.printingService = printingService;
        this.summaryService = summaryService;
    }

    // Metoda do obliczeń rat kredytu. Drukuje informacje o kredycie, listę rat wraz z danymi o ratach
    @Override
    public void calculate(InputData inputData) {
        printingService.printInputDataInfo(inputData);

        List<Installment> installments = installmentCalculationService.calculate(inputData);

        Summary summary = summaryService.calculate(installments);
        printingService.printSummary(summary );

        printingService.printInstallments(installments);
    }
}
