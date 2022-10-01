package model;

import java.math.BigDecimal;

public class MortgageResidual {

    private final BigDecimal ammount;

    private final BigDecimal duration;

    public MortgageResidual(BigDecimal ammount, BigDecimal duration) {
        this.ammount = ammount;
        this.duration = duration;
    }

    public BigDecimal getAmmount() {
        return ammount;
    }

    public BigDecimal getDuration() {
        return duration;
    }
}
