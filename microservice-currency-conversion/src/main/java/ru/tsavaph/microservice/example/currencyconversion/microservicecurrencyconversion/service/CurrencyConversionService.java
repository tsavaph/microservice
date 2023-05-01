package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.bean.CurrencyConversionBean;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.proxy.CurrencyExchangeServiceProxy;

import java.math.BigDecimal;

@Service
@Slf4j
public class CurrencyConversionService {

    private final CurrencyExchangeServiceProxy proxy;

    @Autowired
    public CurrencyConversionService(CurrencyExchangeServiceProxy proxy) {
        this.proxy = proxy;
    }

    public CurrencyConversionBean makeAvgCurrencyConversionBean(String from, String to, Integer year, Integer month, BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveAvgExchangeValue(from, to, year, month);

        log.info("{}", response);

        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));

        return response;
    }

    public CurrencyConversionBean makeCurrencyConversionBean(String from, String to, Integer year, Integer month, Integer day, BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to, year, month, day);

        log.info("{}", response);

        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));

        return response;
    }
}
