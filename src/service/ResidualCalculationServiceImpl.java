package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.MortgageResidual;

import java.math.BigDecimal;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {
    @Override
    public MortgageResidual calculate(InstallmentAmounts installmentAmount, InputData inputData) {
        BigDecimal residualAmount = inputData.getAmount().subtract(installmentAmount.getCapitalAmount());
        BigDecimal residualDuration = inputData.getMonthDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(InstallmentAmounts installmentAmount, Installment previousInstallment) {
        MortgageResidual residual = previousInstallment.getMortgageResidual();

        BigDecimal residualAmount = residual.getAmount().subtract(installmentAmount.getCapitalAmount()).max(BigDecimal.ZERO);
        BigDecimal residualDuration = residual.getDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }
}
