package service;

import model.InputData;
import model.Installment;
import model.MortgageReference;

public interface ReferenceCalculatorService {
    MortgageReference calculate(InputData inputData);
}
