package service;

import model.Installment;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {

    // Implementacja interfejsu SummaryService za pomocą lambdy
    public static SummaryService create() {
        return installments -> {
            BigDecimal interestSum = calculateInterestSum(installments);
            return new Summary(interestSum);
        };
    }

    private static BigDecimal calculateInterestSum(List<Installment> installments) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Installment installment : installments) {
            sum = sum.add(installment.getInstallmentAmounts().getInterestAmount());
        }
        return sum;
    }
}
