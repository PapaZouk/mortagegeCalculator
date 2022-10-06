package service;

import model.InputData;
import model.Installment;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class PrintingServiceImpl implements PrintingService{
    @Override
    public void printInputDataInfo(final InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        printMessage(msg);
    }

    @Override
    public void printInstallments(List<Installment> installments) {
        String format = "%4s %3s " +
                "%4s %6s " +
                "%6s %2s " +
                "%4s %2s " +
                "%4s %8s " +
                "%7s %8s " +
                "%7s %10s " +
                "%7s %10s " +
                "%7s %3s ";

        for (Installment installment : installments) {
            String message = String.format(format,
                    INSTALLMENT_NUMBER, installment.getInstallmentNumber(),
                    DATE, installment.getTimePoint().getDate(),
                    YEAR, installment.getTimePoint().getYear(),
                    MONTH, installment.getTimePoint().getMonth(),
                    INSTALLMENT, installment.getInstallmentAmounts().getInstallmentAmount(),
                    INTEREST, installment.getInstallmentAmounts().getInterestAmount(),
                    CAPITAL, installment.getInstallmentAmounts().getCapitalAmount(),
                    LEFT_AMOUNT, installment.getMortgageResidual().getAmount(),
                    LEFT_MONTHS, installment.getMortgageResidual().getDuration());
            printMessage(new StringBuilder(message));

            if (installment.getInstallmentNumber().remainder(BigDecimal.valueOf(12)).equals(BigDecimal.ZERO)) {
                System.out.println("== Year of payment NR: " + installment.getTimePoint().getYear() +
                        " ======================================================================" +
                        "======================================================================");
            }
        }
    }

    @Override
    public void printSummary(Summary summary) {
        StringBuilder msg = new StringBuilder();
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);

        printMessage(new StringBuilder(msg.toString()));
    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb);
    }
}
