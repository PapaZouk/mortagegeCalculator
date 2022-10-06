import model.InputData;
import model.InstallmentType;
import service. *;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        InputData inputData = new InputData()
                .withRepaymentStartDate(LocalDate.now())
                .withAmount(BigDecimal.valueOf(300000))
                .withMonthDuration(BigDecimal.valueOf(240))
                .withRateType(InstallmentType.CONSTANT);

        PrintingService printingService = new PrintingServiceImpl();
        InstallmentCalculationService installmentCalculationService = new InstallmentCalculationServiceImpl(
                new TimePointSerivceImpl(),
                new AmountCalculationServiceImpl(),
                new ResidualCalculationServiceImpl()
        );


        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                installmentCalculationService ,
                printingService
        );

        mortgageCalculationService.calculate(inputData);


    }
}
