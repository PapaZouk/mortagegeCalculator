package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.Overpayment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static service.AmountCalculationServiceImpl.YEAR;

public class DecreasingAmountsCalculationServiceImpl implements  DecreasingAmountsCalculationService {

    @Override
    public InstallmentAmounts calculate(
            InputData inputData,
            Overpayment overpayment
    ) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = inputData.getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthDuration();

        // część odsetkowa
        InterestAmountFunction getInterestAmount = (residualAmount1, interestPercent1) -> residualAmount
                .multiply(interestPercent)
                .divide(YEAR, 50, RoundingMode.HALF_UP
                );
        BigDecimal interestAmount = getInterestAmount.interestAmountCalculate(residualAmount,interestPercent);

        BigDecimal capitalAmount = calculateCapitalAmount(
                referenceAmount,
                referenceDuration,
                residualAmount
        );

        BigDecimal installmentAmount = capitalAmount.add(interestAmount);

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    @Override
    public InstallmentAmounts calculate(
            InputData inputData,
            Overpayment overpayment,
            Installment previousInstallment
    ) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = previousInstallment.getMortgageResidual().getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthDuration();


        // część odsetkowa
        InterestAmountFunction getInterestAmount = (residualAmount1, interestPercent1) -> residualAmount
                .multiply(interestPercent)
                .divide(YEAR, 50, RoundingMode.HALF_UP
                );
        BigDecimal interestAmount = getInterestAmount.interestAmountCalculate(residualAmount, interestPercent);

        BigDecimal capitalAmount = calculateCapitalAmount(
                referenceAmount,
                referenceDuration,
                residualAmount
        );

        BigDecimal installmentAmount = capitalAmount.add(interestAmount);

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateCapitalAmount(
            BigDecimal amount,
            BigDecimal monthDuration,
            BigDecimal residualAmount
    ) {
        BigDecimal capitalAmount = amount.divide(monthDuration, 50, RoundingMode.HALF_UP);
        if(capitalAmount.compareTo(residualAmount) >= 0) {
            return residualAmount;
        }
        return capitalAmount;
    }

}
