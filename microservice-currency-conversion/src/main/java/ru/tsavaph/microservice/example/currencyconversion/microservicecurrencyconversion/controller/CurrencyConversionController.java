package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.bean.CurrencyConversionBean;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.service.CurrencyConversionService;

import java.math.BigDecimal;

@RestController
@RequestMapping(path="/currency-converter")
@Validated
public class CurrencyConversionController {

    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("/from/{from}/to/{to}/year/{year}/month/{month}/quantity/{quantity}")
    public CurrencyConversionBean convertAvgCurrency(@PathVariable @Length(min = 3, max = 3) String from,
                                                  @PathVariable @Length(min = 3, max = 3) String to,
                                                  @PathVariable @Min(1900) @Max(2100) Integer year,
                                                  @PathVariable @Min(1) @Max(12)  Integer month,
                                                  @PathVariable @Positive BigDecimal quantity) {

        return currencyConversionService.makeAvgCurrencyConversionBean(from, to, year, month, quantity);
    }

    @GetMapping("/from/{from}/to/{to}/year/{year}/month/{month}/day/{day}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable @Length(min = 3, max = 3) String from,
                                                  @PathVariable @Length(min = 3, max = 3) String to,
                                                  @PathVariable @Min(1900) @Max(2100) Integer year,
                                                  @PathVariable @Min(1) @Max(12)  Integer month,
                                                  @PathVariable @Min(1) @Max(31)  Integer day,
                                                  @PathVariable @Positive BigDecimal quantity) {

        return currencyConversionService.makeCurrencyConversionBean(from, to, year, month, day, quantity);
    }
}
