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

    public CurrencyConversionBean makeCurrencyConversionBean(String from, String to, BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

        log.info("{}", response);

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
