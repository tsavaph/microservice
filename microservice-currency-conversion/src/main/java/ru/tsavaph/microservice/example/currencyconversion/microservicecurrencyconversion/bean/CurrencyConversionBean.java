package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CurrencyConversionBean {

    @NotNull
    @Length(min = 3, max = 3)
    private String from;
    @NotNull
    @Length(min = 3, max = 3)
    private String to;
    @Min(1900)
    @Max(2100)
    @NotNull
    private Integer year;
    @Min(1)
    @Max(12)
    @NotNull
    private Integer month;
    @Min(1)
    @Max(31)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer day;
    @NotNull
    @Positive
    private BigDecimal conversionMultiple;
    @NotNull
    @Positive
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
}
