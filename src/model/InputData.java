package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

// Symuluje dane wejściowe do programu
public class InputData {

    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);

    // Data rozpoczęcia kredytu
    private LocalDate repaymentStartDate = LocalDate.of(2020, 1, 6);

    // Stawka WIBOR
    private BigDecimal wiborPercent = new BigDecimal("6.95");

    // Kwota kredytu
    private BigDecimal amount = new BigDecimal("300000");

    // Czas trwania kredytu
    private BigDecimal monthDuration = new BigDecimal("240");

    // Typ spłacanych rat (stałe / malejące)
    private InstallmentType installmentType = InstallmentType.CONSTANT;

    // Marża banku
    private BigDecimal bankMarginPercent = new BigDecimal("1.9");


    // withery
    public InputData withRepaymentStartDate(LocalDate repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
        return this;
    }

    public InputData withWiborPercentage(BigDecimal wiborPercentage) {
        this.wiborPercent = wiborPercentage;
        return this;
    }

    public InputData withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InputData withMonthDuration(BigDecimal monthDuration) {
        this.monthDuration = monthDuration;
        return this;
    }

    public InputData withRateType(InstallmentType installmentType) {
        this.installmentType = installmentType;
        return this;
    }

    public InputData withBankMarginPercent(BigDecimal bankMarginPercent) {
        this.bankMarginPercent = bankMarginPercent;
        return this;
    }

    public LocalDate getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMonthDuration() {
        return monthDuration;
    }

    public InstallmentType getInstallmentType() {
        return installmentType;
    }

    public BigDecimal getInterestPercent() {
        return wiborPercent.add(bankMarginPercent).divide(PERCENT, 10, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }
}
