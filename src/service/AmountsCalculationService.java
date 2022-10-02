package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;

public interface AmountsCalculationService {
    InstallmentAmounts calculate(InputData inputData);

    InstallmentAmounts calculate(InputData inputData, Installment previousInstallment);
}
