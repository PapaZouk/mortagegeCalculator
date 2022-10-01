package model;

import java.math.BigDecimal;

public class Installment {

    private final TimePoint timePoint;

    private final BigDecimal installmentNumber;

    private final InstallmentAmounts installmentAmounts;

    private final MortgageResidual mortgageResidual;

    public Installment(
            TimePoint timePoint,
            BigDecimal installmentNumber,
            InstallmentAmounts installmentAmounts,
            MortgageResidual mortgageResidual)
    {
        this.timePoint = timePoint;
        this.installmentNumber = installmentNumber;
        this.installmentAmounts = installmentAmounts;
        this.mortgageResidual = mortgageResidual;
    }

    public TimePoint getTimePoint() {
        return timePoint;
    }

    public BigDecimal getInstallmentNumber() {
        return installmentNumber;
    }

    public InstallmentAmounts getInstallmentAmounts() {
        return installmentAmounts;
    }

    public MortgageResidual getMortgageResidual() {
        return mortgageResidual;
    }
}
