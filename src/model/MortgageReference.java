package model;

import java.math.BigDecimal;

public class MortgageReference {
    /*
    Klasa przechowuje referencję do kwoty kredytu pomniejszonej o każdą nową nadpłatę.
    Po każdej nadpłacie klasa przechowuję nową, stałą kwotę kredytu do spłaty.
     */

    private final BigDecimal referenceAmount;

    private final BigDecimal referenceDuration;

    public MortgageReference(BigDecimal referenceAmount, BigDecimal referenceDuration) {
        this.referenceAmount = referenceAmount;
        this.referenceDuration = referenceDuration;
    }

    public BigDecimal getReferenceAmount() {
        return referenceAmount;
    }

    public BigDecimal getReferenceDuration() {
        return referenceDuration;
    }
}
