package service;

import model.InputData;
import model.Installment;
import model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculatorService {

    Overpayment calculate(BigDecimal installmentNumber, InputData inputData);
}
