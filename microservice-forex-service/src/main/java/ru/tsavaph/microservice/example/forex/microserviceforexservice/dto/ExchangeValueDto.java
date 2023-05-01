package ru.tsavaph.microservice.example.forex.microserviceforexservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeValueDto {
    @NotNull
    @Length(min = 3, max = 3)
    private String from;
    @NotNull
    @Length(min = 3, max = 3)
    private String to;

    @Min(1)
    @Max(12)
    @NotNull
    private Integer month;
    @Min(1)
    @Max(31)
    @JsonInclude(Include.NON_NULL)
    private Integer day;
    @Min(1900)
    @Max(2100)
    @NotNull
    private Integer year;

    @NotNull
    @Positive
    private BigDecimal conversionMultiple;

    public ExchangeValueDto(String from, String to, Integer month, Integer year, BigDecimal conversionMultiple) {
        this.from = from;
        this.to = to;
        this.month = month;
        this.year = year;
        this.conversionMultiple = conversionMultiple;
    }
}
