package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Overpayment {

    // Dwie strategie przy nadpłacie raty:
    // 1. redukcja wielkości rat
    // 2. redukcja czasu kredytowania
    public static final String REDUCE_RATE = "REDUCE_RATE";
    public static final String REDUCE_PERIOD = "REDUCE_PERIOD";

    private final BigDecimal amount;

    private final BigDecimal provisionAmount;

    public Overpayment(BigDecimal amount, BigDecimal provisionAmount) {
        this.amount = amount;
        this.provisionAmount = provisionAmount;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getProvisionAmount() {
        return provisionAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
