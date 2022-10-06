package service;

import model.Installment;

import java.math.BigDecimal;

public interface Function {
    BigDecimal calculate(Installment installment);
}
