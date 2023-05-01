package ru.tsavaph.microservice.example.forex.microserviceforexservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
    private Integer month;
    @JsonInclude(Include.NON_NULL)
    private Integer day;
    private Integer year;
    private BigDecimal conversionMultiple;

    public ExchangeValueDto(String from, String to, Integer month, Integer year, BigDecimal conversionMultiple) {
        this.from = from;
        this.to = to;
        this.month = month;
        this.year = year;
        this.conversionMultiple = conversionMultiple;
    }
}
