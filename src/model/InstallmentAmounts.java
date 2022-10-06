package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InstallmentAmounts {

    /* Obiekt raty przechowujący informacje:
        - o jej wielkości,
        - stopie procentowej,
        - pozostałej kwocie kredytu do spłaty

     */

    private final BigDecimal installmentAmount;

    private final BigDecimal interestAmount;

    private final BigDecimal capitalAmount;

    private final Overpayment overpayment;

    public InstallmentAmounts(
            BigDecimal installmentAmount,
            BigDecimal interestAmount,
            BigDecimal capitalAmount,
            Overpayment overpayment
    ) {
        this.installmentAmount = installmentAmount;
        this.interestAmount = interestAmount;
        this.capitalAmount = capitalAmount;
        this.overpayment = overpayment;
    }

    public Overpayment getOverpayment() {
        return overpayment;
    }

    public BigDecimal getInstallmentAmount() {
        return installmentAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestAmount() {
        return interestAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCapitalAmount() {
        return capitalAmount.setScale(2, RoundingMode.HALF_UP);
    }

}
