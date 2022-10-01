import model.InputData;
import model.RateType;
import service.PrintingService;
import service.PrintingServiceImpl;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        InputData inputData = new InputData()
                .withAmount(BigDecimal.valueOf(4000000))
                .withMonthDuration(BigDecimal.valueOf(360))
                .withRateType(RateType.DECREASING);

        PrintingService printingService = new PrintingServiceImpl();
        printingService.printInputDataInfo(inputData);
    }
}
