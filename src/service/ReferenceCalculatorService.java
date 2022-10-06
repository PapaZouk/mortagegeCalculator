package service;

import model.Installment;
import model.MortgageReference;

public interface ReferenceCalculatorService {
    MortgageReference calculate();

    MortgageReference calculate(Installment previousInstallment);
}
