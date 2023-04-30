package ru.tsavaph.microservice.example.forex.microserviceforexservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeValueDto {
    private String from;
    private String to;
    private Integer year;

    private Integer month;
    private BigDecimal conversionMultiple;
}
