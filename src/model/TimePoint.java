package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimePoint {

    // Obiekt czasowy dla pojedynczej raty

    private final LocalDate date;

    private final BigDecimal year;

    private final BigDecimal month;

    public TimePoint( LocalDate date, BigDecimal year, BigDecimal month) {
        this.date = date;
        this.year = year;
        this.month = month;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getYear() {
        return year;
    }

    public BigDecimal getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return "TimePoint{" +
                "date=" + date +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
