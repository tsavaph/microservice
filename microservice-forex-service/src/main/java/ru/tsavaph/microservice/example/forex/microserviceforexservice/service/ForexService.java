package ru.tsavaph.microservice.example.forex.microserviceforexservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.repo.ExchangeValueRepository;

import java.math.BigDecimal;

@Service
public class ForexService {
    private final ExchangeValueRepository repository;


    @Autowired
    public ForexService(ExchangeValueRepository repository) {
        this.repository = repository;
    }

    public BigDecimal findAngConversionByFromToYearAndMonth(String from, String to, Integer year, Integer month) {

        BigDecimal exchangeValue = repository.findAngConversionByFromToYearMonth(from, to, year, month);

        return exchangeValue;
    }
}
