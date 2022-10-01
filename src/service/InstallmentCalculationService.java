package service;

import model.InputData;
import model.Installment;

import java.util.List;

public interface InstallmentCalculationService {

    // Metoda zwracająca listę rat do spłaty i powiązane z tymi ratami informacje, np. kwota, data etc.
    List<Installment> calculate(final InputData inputData);

}
