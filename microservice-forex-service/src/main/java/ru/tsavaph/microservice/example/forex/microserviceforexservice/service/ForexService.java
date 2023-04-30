package ru.tsavaph.microservice.example.forex.microserviceforexservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.repo.ExchangeValueRepository;

@Service
public class ForexService {
    private final ExchangeValueRepository repository;


    @Autowired
    public ForexService(ExchangeValueRepository repository) {
        this.repository = repository;
    }

    public ExchangeValue findExchangeValueByFromAndToAndYearAndMonth(String from, String to, Integer year, Integer month) {

        return repository.findByFromAndToAndYearAndMonth(from, to, year, month);
    }
}
