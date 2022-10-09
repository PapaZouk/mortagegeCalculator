package service;

import model.InputData;
import model.Installment;
import model.MortgageReference;

import java.math.BigDecimal;

public class ReferenceCalculatorServiceImpl implements ReferenceCalculatorService{

    // Referencje ma zastosowanie, gdy będzie liczony nowy czas trwania kredytu, a nie wielkość raty (rata będzie stała).
    @Override
    public MortgageReference calculate(InputData inputData) {
        return new MortgageReference(inputData.getAmount(), inputData.getMonthDuration());
    }
}
