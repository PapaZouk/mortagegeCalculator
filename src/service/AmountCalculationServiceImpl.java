package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.Overpayment;
import model.exception.InstallmentCalculationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountCalculationServiceImpl implements AmountsCalculationService {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    // Liczy wielkość raty na początku jej spłacania. Metoda
    // wspiera dwa scenariusze:
    // - kredyt o ratach stałych,
    // - kredyt o ratach malejących
    @Override
    public InstallmentAmounts  calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getInstallmentType()) {
            case CONSTANT:
                return calculateConstantInstallment(inputData, overpayment);
            case DECREASING:
                return calculateDecreasingInstallment(inputData, overpayment);
            default:
                throw new InstallmentCalculationException();
        }
    }

    // Liczy wielkość raty z uwzględnieniem poprzedniej raty
    @Override
    public InstallmentAmounts calculate(InputData inputData, Overpayment overpayment, Installment previousInstallment) {
        switch (inputData.getInstallmentType()) {
            case CONSTANT:
                return calculateConstantInstallment(inputData, overpayment, previousInstallment);
            case DECREASING:
                return calculateDecreasingInstallment(inputData, overpayment, previousInstallment);
            default:
                throw new InstallmentCalculationException();
        }
    }

    private InstallmentAmounts calculateConstantInstallment(InputData inputData,  Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = inputData.getAmount();

        BigDecimal q = calculateQ(interestPercent);

        BigDecimal installmentAmount = calculateConstantInstallmentAmount(q, inputData.getAmount(), inputData.getMonthDuration());
        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateConstantCapitalAmount(installmentAmount, interestAmount);

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    private InstallmentAmounts calculateConstantInstallment(InputData inputData,  Overpayment overpayment, Installment previousInstallment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = previousInstallment.getMortgageResidual().getAmount();

        BigDecimal q = calculateQ(interestPercent);

        BigDecimal installmentAmount = calculateConstantInstallmentAmount(q, inputData.getAmount(), inputData.getMonthDuration());
        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent); // część odsetkowa
        BigDecimal capitalAmount = calculateConstantCapitalAmount(installmentAmount, interestAmount); // część kapitałowa

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateConstantInstallmentAmount(
            BigDecimal q,
            BigDecimal amount,
            BigDecimal monthDuration)
    {
        return amount
                .multiply(q.pow(monthDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthDuration.intValue())
                        .subtract(BigDecimal.ONE), 50, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateConstantCapitalAmount(BigDecimal installmentAmount, BigDecimal interestAmount) {
        return installmentAmount.subtract(interestAmount);
    }

    private InstallmentAmounts calculateDecreasingInstallment(InputData inputData,  Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = inputData.getAmount();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(residualAmount, inputData.getMonthDuration());
         BigDecimal installmentAmount = capitalAmount.add(interestAmount);

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateDecreasingCapitalAmount(BigDecimal amount, BigDecimal monthDuration) {
        return amount.divide(monthDuration, 50, RoundingMode.HALF_UP);
    }

    private InstallmentAmounts calculateDecreasingInstallment(InputData inputData,  Overpayment overpayment, Installment previousInstallment) {
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal residualAmount = previousInstallment.getMortgageResidual().getAmount();

        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(inputData.getAmount(), inputData.getMonthDuration());
        BigDecimal installmentAmount = capitalAmount.add(interestAmount);

        return new InstallmentAmounts(installmentAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calculateQ(BigDecimal interestPercent) {
        return interestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    private BigDecimal calculateInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
    return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }
}
