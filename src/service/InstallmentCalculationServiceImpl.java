package service;

import model.InputData;
import model.Installment;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class InstallmentCalculationServiceImpl implements InstallmentCalculationService {

    // Metoda zlicza wielkość raty na podstawie danych podanych z klasy InputData
    @Override
    public List<Installment> calculate(InputData inputData) {
        // Lista przechowująca raty
        List<Installment> installments = new LinkedList<>();

        // Rata ZEROWA
        BigDecimal installmentNumber = BigDecimal.ONE;

        Installment firstInstallment = calculateFirstInstallment(installmentNumber, inputData);

        installments.add(firstInstallment);

        Installment previousInstallment = firstInstallment;

    for (
            BigDecimal index = installmentNumber.add(BigDecimal.ONE);
            index.compareTo(inputData.getMonthDuration()) <= 0;
            index.add(BigDecimal.ONE)
    ) {
      Installment nextInstallment = calculateNextRate(index, inputData, previousInstallment);
      installments.add(nextInstallment);
      previousInstallment = nextInstallment;
    }

        return installments;
    }

    private Installment calculateFirstInstallment(BigDecimal installmentNumber, InputData inputData) {
        return null;
    }

    private Installment calculateNextRate(BigDecimal index, InputData inputData, Installment installment) {
        return null;
    }
}
