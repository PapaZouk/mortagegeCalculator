package service;

import model.Installment;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {

    // Implementacja interfejsu SummaryService za pomocą lambdy
    public static SummaryService create() {
        return installments -> {
            BigDecimal interestSum = calculate(
                    installments,
                    installment -> installment.getInstallmentAmounts().getInterestAmount()
            );

            BigDecimal provisions = calculate(
                    installments,
//                    installment -> installment.getInstallmentAmounts().getOverpayment().getAmount()
                    installment -> installment.getInstallmentAmounts().getOverpayment().getProvisionAmount()
            );

            BigDecimal totalLost = interestSum.add(provisions);

            return new Summary(interestSum, provisions, totalLost);
        };
    }

    private static BigDecimal calculate(List<Installment> installments, Function function) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Installment installment : installments) {
            sum = sum.add(function.calculate(installment));
        }
        return sum;
    }

}
