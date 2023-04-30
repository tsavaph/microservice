package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionBean {
    private String from;
    private String to;
    private Integer year;
    private Integer month;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
}
