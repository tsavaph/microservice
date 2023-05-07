package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bean.CurrencyConversionBean;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy.CurrencyCalculatorServiceProxy;

import java.math.BigDecimal;

@Service
@Slf4j
public class CurrencyCalculatorService {

    private final CurrencyCalculatorServiceProxy proxy;

    @Autowired
    public CurrencyCalculatorService(CurrencyCalculatorServiceProxy proxy) {
        this.proxy = proxy;
    }

    public CurrencyConversionBean makeAvgCurrencyConversionBean(String from, String to, Integer year, Integer month, BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveAvgExchangeValue(from, to, year, month, quantity);

        log.info("{}", response);

        return response;
    }

    public CurrencyConversionBean makeCurrencyConversionBean(String from, String to, Integer year, Integer month, Integer day, BigDecimal quantity) {

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to, year, month, day, quantity);

        log.info("{}", response);

        return response;
    }
}
