package ru.tsavaph.microservice.example.forex.microserviceforexservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.repo.ExchangeValueRepository;

import java.util.Objects;

@Service
public class ForexService {
    private final Environment environment;
    private final ExchangeValueRepository repository;

    @Autowired
    public ForexService(Environment environment, ExchangeValueRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    public ExchangeValue findExchangeValueByFromAndTo(String from, String to) {
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));

        return exchangeValue;
    }
}
