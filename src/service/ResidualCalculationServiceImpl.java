package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.MortgageResidual;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {
    @Override
    public MortgageResidual calculate(InstallmentAmounts installmentAmount, InputData inputData) {
        BigDecimal residualAmount = calculateResidualAmount (inputData.getAmount(), installmentAmount);
        BigDecimal residualDuration = inputData.getMonthDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(InstallmentAmounts installmentAmount, Installment previousInstallment) {
        MortgageResidual residual = previousInstallment.getMortgageResidual();

        BigDecimal residualAmount = calculateResidualAmount(residual.getAmount(), installmentAmount);
        BigDecimal residualDuration = residual.getDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    private static BigDecimal calculateResidualAmount(BigDecimal inputData, InstallmentAmounts installmentAmount) {
        return inputData
                .subtract(installmentAmount.getCapitalAmount())
                .subtract(installmentAmount.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
