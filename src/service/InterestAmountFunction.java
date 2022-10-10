package service;

import java.math.BigDecimal;

public interface InterestAmountFunction {

    BigDecimal interestAmountCalculate(BigDecimal residualAmount, BigDecimal interestPercent);
}
