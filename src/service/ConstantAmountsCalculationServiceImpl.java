package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.Overpayment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static service.AmountCalculationServiceImpl.YEAR;

public class ConstantAmountsCalculationServiceImpl implements ConstantAmountsCalculationService{

    @Override
    public InstallmentAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = inputData.getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthDuration();

        CalculateQFunction getQ = qParameter -> interestPercent
                .divide(YEAR, 10, RoundingMode.HALF_UP)
                .add(BigDecimal.ONE);

        BigDecimal q = getQ.qCalculator(interestPercent);


        // część odsetkowa
        InterestAmountFunction getInterestAmount = (residualAmount1, interestPercent1) -> residualAmount
                .multiply(interestPercent)
                .divide(YEAR, 50, RoundingMode.HALF_UP
                );
        BigDecimal interestAmount = getInterestAmount.interestAmountCalculate(residualAmount, interestPercent);

        BigDecimal installmentAmount = calculateConstantInstallmentAmount(
                q,
                referenceAmount,
                referenceDuration,
                residualAmount,
                interestAmount
        );

        BigDecimal capitalAmount = calculateCapitalAmount(installmentAmount, interestAmount, residualAmount);

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    @Override
    public InstallmentAmounts calculate(
            InputData inputData,
            Overpayment overpayment,
            Installment previousInstallment
    ) {
        BigDecimal residualAmount = previousInstallment.getMortgageResidual().getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthDuration();
        BigDecimal interestPercent = inputData.getInterestPercent();

        CalculateQFunction getQ = qParameter -> interestPercent
                .divide(YEAR, 10, RoundingMode.HALF_UP)
                .add(BigDecimal.ONE);

        BigDecimal q = getQ.qCalculator(interestPercent);


        // część odsetkowa
        InterestAmountFunction getInterestAmount = (residualAmount1, interestPercent1) -> residualAmount
                .multiply(interestPercent)
                .divide(YEAR, 50, RoundingMode.HALF_UP
                );

        BigDecimal interestAmount = getInterestAmount.interestAmountCalculate(residualAmount, interestPercent);

        BigDecimal installmentAmount = calculateConstantInstallmentAmount(
                q,
                referenceAmount,
                referenceDuration,
                interestAmount,
                residualAmount
                );

        BigDecimal capitalAmount = calculateCapitalAmount(
                installmentAmount,
                interestAmount,
                residualAmount
        ); // część kapitałowa

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateConstantInstallmentAmount(
            BigDecimal q,
            BigDecimal amount,
            BigDecimal monthDuration,
            BigDecimal interestAmount,
            BigDecimal residualAmount
    ) {
        BigDecimal rateAmount = amount
                .multiply(q.pow(monthDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthDuration.intValue())
                        .subtract(BigDecimal.ONE), 50, RoundingMode.HALF_UP);

        return compareWithResidual(rateAmount, interestAmount, residualAmount);
    }

    private BigDecimal compareWithResidual(
            BigDecimal rateAmount,
            BigDecimal interestAmount,
            BigDecimal residualAmount
    ) {
        if(rateAmount.subtract(interestAmount).compareTo(residualAmount) >= 0) {
            return residualAmount.add(interestAmount);
        }
        return rateAmount;
    }

    private BigDecimal calculateCapitalAmount(
            BigDecimal installmentAmount,
            BigDecimal interestAmount,
            BigDecimal residualAmount
    ) {
        BigDecimal capitalAmount = installmentAmount.subtract(interestAmount);

        if(capitalAmount.compareTo(residualAmount) >= 0) {
            return residualAmount;
        }
        return capitalAmount;
    }

}
