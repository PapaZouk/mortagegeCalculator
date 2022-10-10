package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

// Symuluje dane wejściowe do programu
public class InputData {

    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);

    // Data rozpoczęcia kredytu
    private LocalDate repaymentStartDate = LocalDate.of(2020, 1, 6);

    // Stawka WIBOR
    private BigDecimal wiborPercent = new BigDecimal("6.75");

    // Kwota kredytu
    private BigDecimal amount = new BigDecimal("300000");

    // Czas trwania kredytu
    private BigDecimal monthDuration = new BigDecimal("180");

    // Typ spłacanych rat (stałe / malejące)
    private InstallmentType installmentType = InstallmentType.CONSTANT;

    // Marża banku
    private BigDecimal bankMarginPercent = new BigDecimal("1.2");

    // Mapa przechowuje miesiąc, w którym występuje nadpłata oraz kwotę wysokości nadpłaty.
    // Schemat zakłada, że najpierw danego miesiąca jest dokonana spłata regularna,
    // a dopiero następnie dokonywana jest nadpłata. Nie odwrotnie.
    private Map<Integer, BigDecimal> overpaymentSchema = Map.of(
            5, BigDecimal.valueOf(5000),
            6, BigDecimal.valueOf(5000),
            42, BigDecimal.valueOf(5000),
            50, BigDecimal.valueOf(5000),
            62, BigDecimal.valueOf(6000)
    );

    // Klasa Overpayment przetrzymuje wartości nadpłat.
    private String overpaymentReduceWay = Overpayment.REDUCE_PERIOD;


    // Przechowuje liczbę miesięcy, w trakcie których nie powinna być dokonywana nadpłata.
    // W innym przypadku zostaje naliczona prowizja.
    private BigDecimal overpaymentProvisionMonths = BigDecimal.valueOf(36);

    // Przechowuje wielkość prowizji (W procentach) doliczanej w przypadku nadpłaty w okresie objętym zakazem nadpłat.
    private BigDecimal overpaymentProvisionPercent = BigDecimal.valueOf(3);



    public InputData withOverpaymentSchema(Map<Integer, BigDecimal> overpaymentSchema) {
        this.overpaymentSchema = overpaymentSchema;
        return this;
    };
    public InputData withOverpaymentReduceWay(String overpaymentReduceWay) {
        this.overpaymentReduceWay = overpaymentReduceWay;
        return this;
    };
    public InputData withOverpaymentProvisionMonths(BigDecimal overpaymentProvisionMonths) {
        this.overpaymentProvisionMonths = overpaymentProvisionMonths;
        return this;
    };
    public InputData withOverpaymentProvisionPercent(BigDecimal overpaymentProvisionPercent) {
        this.overpaymentProvisionPercent = overpaymentProvisionPercent;
        return this;
    };


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
        return wiborPercent.add(bankMarginPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return wiborPercent.add(bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }

    public Map<Integer, BigDecimal> getOverpaymentSchema() {
        return overpaymentSchema;
    }

    public String getOverpaymentReduceWay() {
        return overpaymentReduceWay;
    }

    public BigDecimal getOverpaymentProvisionMonths() {
        return overpaymentProvisionMonths;
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }
}
