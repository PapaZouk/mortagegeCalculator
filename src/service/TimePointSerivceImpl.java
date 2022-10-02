package service;

import model.InputData;
import model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimePointSerivceImpl implements TimePointService {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public TimePoint calculate(BigDecimal installmentNumber, InputData inputData) {
        LocalDate date = calculateDate(installmentNumber, inputData);
        BigDecimal year = calculateYear(installmentNumber);
        BigDecimal month = calculateMonth(installmentNumber);

//        return new TimePoint();
        return new TimePoint(date, year, month);
    }

    // Liczy datę dla konkretnej spłacanej raty
    private static LocalDate calculateDate(BigDecimal installmentNumber, InputData inputData) {
        return inputData.getRepaymentStartDate()
                .plus(installmentNumber.subtract(BigDecimal.ONE).intValue(), ChronoUnit.MONTHS);
    }

    // Liczy rok spłaty danej raty
    private BigDecimal calculateYear(final BigDecimal installmentNumber) {
        return installmentNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    // Liczy miesiąc raty względem roku
    private BigDecimal calculateMonth(final BigDecimal installmentNumber) {
        return BigDecimal.ZERO.equals(installmentNumber.remainder(YEAR)) ? YEAR : installmentNumber.remainder(YEAR);
    }
}
