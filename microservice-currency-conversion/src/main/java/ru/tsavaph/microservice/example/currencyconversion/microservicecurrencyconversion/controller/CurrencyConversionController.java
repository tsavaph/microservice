package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.bean.CurrencyConversionBean;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.service.CurrencyConversionService;

import java.math.BigDecimal;

@RestController
@RequestMapping(path="/currency-converter")
public class CurrencyConversionController {

    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        return currencyConversionService.makeCurrencyConversionBean(from, to, quantity);
    }
}
