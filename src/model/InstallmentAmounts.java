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

    private final  BigDecimal capitalAmount;

    public InstallmentAmounts(
            BigDecimal installmentAmount,
            BigDecimal interestAmount,
            BigDecimal capitalAmount)
    {
        this.installmentAmount = installmentAmount;
        this.interestAmount = interestAmount;
        this.capitalAmount = capitalAmount;
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

    @Override
    public String toString() {
        return "InstallmentAmounts{" +
                "installmentAmount=" + installmentAmount +
                ", interestAmount=" + interestAmount +
                ", capitalAmount=" + capitalAmount +
                '}';
    }
}
