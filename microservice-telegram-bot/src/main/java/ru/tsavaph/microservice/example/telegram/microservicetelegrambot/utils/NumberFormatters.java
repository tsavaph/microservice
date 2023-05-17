package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberFormatters {
    public static BigDecimal formatBigDecimal(BigDecimal number, int maxDigits) {
        BigDecimal truncatedNumber = number.setScale(0, RoundingMode.DOWN);

        if (truncatedNumber.precision() > maxDigits) {
            return truncatedNumber;
        } else if (number.compareTo(BigDecimal.ONE) < 0) {
            return number.setScale(maxDigits - truncatedNumber.precision() + 1, RoundingMode.HALF_UP).stripTrailingZeros();
        } else {
            return number.setScale(maxDigits - truncatedNumber.precision(), RoundingMode.HALF_UP).stripTrailingZeros();
        }
    }
}
