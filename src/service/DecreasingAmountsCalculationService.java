package service;

import model.InputData;
import model.Installment;
import model.InstallmentAmounts;
import model.Overpayment;

public interface DecreasingAmountsCalculationService {


    InstallmentAmounts calculate(InputData inputData, Overpayment overpayment);

    InstallmentAmounts calculate(InputData inputData, Overpayment overpayment, Installment previousInstallment);
}
