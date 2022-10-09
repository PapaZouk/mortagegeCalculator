package service;

import model.InputData;
import model.Overpayment;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class OverpaymentCalculatorServiceImpl implements OverpaymentCalculatorService {

    @Override
    public Overpayment calculate(BigDecimal installmentNumber, InputData inputData) {
        BigDecimal overpaymentAmount = calculateAmount(installmentNumber, inputData.getOverpaymentSchema())
                .orElse(BigDecimal.ZERO);
        BigDecimal overpaymentProvision = calculateProvision(installmentNumber, overpaymentAmount, inputData);

        return new Overpayment(overpaymentAmount, overpaymentProvision);
    }

    private Optional<BigDecimal> calculateAmount(
            BigDecimal installmentNumber,
            Map<Integer, BigDecimal> overpaymentSchema
    ) {
        for (Map.Entry<Integer, BigDecimal> entry : overpaymentSchema.entrySet()) {
            if (installmentNumber.equals(BigDecimal.valueOf(entry.getKey()))) {
                return Optional.of(entry.getValue());
            }
        }
        return Optional.empty();
    }

    // Zlicza wysokość prowizji przy nadpłacie w okresie, w którym do nadpłat jest doliczana prowizji.
    private BigDecimal calculateProvision(
            BigDecimal installmentNumber,
            BigDecimal overpaymentAmount,
            InputData inputData
    ) {
        if(BigDecimal.ZERO.equals(overpaymentAmount)) { // Jeżeli nadpłata nie nastąpiła
            return BigDecimal.ZERO;
        }
        if(installmentNumber.compareTo(inputData.getOverpaymentProvisionMonths()) > 0) { // Jeżeli nr raty jest poza okresem naliczania prowizji
            return BigDecimal.ZERO;
        }

        return overpaymentAmount.multiply(inputData.getOverpaymentProvisionPercent());
    }
}
