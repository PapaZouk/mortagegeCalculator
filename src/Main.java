import model.InputData;
import model.InstallmentType;
import service.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        InputData inputData = new InputData()
                .withRepaymentStartDate(LocalDate.now())
                .withAmount(BigDecimal.valueOf(300000))
                .withMonthDuration(BigDecimal.valueOf(280))
                .withRateType(InstallmentType.DECREASING);

        PrintingService printingService = new PrintingServiceImpl();
        InstallmentCalculationService installmentCalculationService = new InstallmentCalculationServiceImpl(
                new TimePointSerivceImpl(),
                new AmountCalculationServiceImpl(),
                new OverpaymentCalculatorServiceImpl(),
                new ReferenceCalculatorServiceImpl(),
                new ResidualCalculationServiceImpl()
        );


        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                installmentCalculationService,
                printingService,
                SummaryServiceFactory.create()
        );

        mortgageCalculationService.calculate(inputData);


    }
}
