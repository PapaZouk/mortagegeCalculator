package service;

import model.InputData;
import model.Installment;
import model.Overpayment;
import model.Summary;
import model.exception.MortgageException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {
    @Override
    public void printInputDataInfo(final InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);

        Optional.of(inputData.getOverpaymentSchema())
                .filter(schema -> schema.size() > 0).ifPresent(schema -> logOverpayment(msg, inputData));

        printMessage(msg);
    }

    private void logOverpayment(StringBuilder msg, InputData inputData) {
        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortgageException();
        }
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(inputData.getOverpaymentSchema());
        msg.append(NEW_LINE);
    }

    @Override
    public void printInstallments(List<Installment> installments) {
        String format = "%4s %3s | " +
                "%4s %6s | " +
                "%6s %2s | " +
                "%4s %2s | " +
                "%4s %8s | " +
                "%7s %8s | " +
                "%7s %10s | " +
                "%7s %10s | " +
                "%7s %10s | " +
                "%7s %3s | ";

        for (Installment installment : installments) {
            String message = String.format(format,
                    INSTALLMENT_NUMBER, installment.getInstallmentNumber(),
                    DATE, installment.getTimePoint().getDate(),
                    YEAR, installment.getTimePoint().getYear(),
                    MONTH, installment.getTimePoint().getMonth(),
                    INSTALLMENT, installment.getInstallmentAmounts().getInstallmentAmount(),
                    INTEREST, installment.getInstallmentAmounts().getInterestAmount(),
                    CAPITAL, installment.getInstallmentAmounts().getCapitalAmount(),
                    OVERPAYMENT, installment.getInstallmentAmounts().getOverpayment().getAmount(),
                    LEFT_AMOUNT, installment.getMortgageResidual().getAmount(),
                    LEFT_MONTHS, installment.getMortgageResidual().getDuration());

            printMessage(new StringBuilder(message));

            if (installment.getInstallmentNumber().remainder(BigDecimal.valueOf(12)).equals(BigDecimal.ZERO)) {
                System.out.println("== Year of payment NR: " + installment.getTimePoint().getYear() +
                        " =============================================" +
                        "=============================================" +
                        "=============================================" +
                        "=============================================");
            }
        }
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public void printSummary(Summary summary) {
        StringBuilder msg = new StringBuilder();
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisionSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOST_SUM).append(summary.getTotalLost()).append(CURRENCY);
        msg.append(NEW_LINE);

        printMessage(new StringBuilder(msg.toString()));
    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb);
    }
}
