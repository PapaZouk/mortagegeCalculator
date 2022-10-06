package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.MortgageResidual;

public interface ResidualCalculationService {
    MortgageResidual calculate(InstallmentAmounts installmentAmount, InputData inputData);

    MortgageResidual calculate(InstallmentAmounts installmentAmount, Installment previousInstallment);
}
