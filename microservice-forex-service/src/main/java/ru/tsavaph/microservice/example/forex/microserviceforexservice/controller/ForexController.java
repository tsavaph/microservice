package ru.tsavaph.microservice.example.forex.microserviceforexservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.dto.ExchangeValueDto;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.mapper.ExchangeValueMapper;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.service.ForexService;

import java.util.Objects;

@RestController
@RequestMapping(path = "/currency-exchange")
public class ForexController {
    private final ExchangeValueMapper mapper;

    private final ForexService service;
    private final Environment environment;

    @Autowired
    public ForexController(ForexService service, ExchangeValueMapper mapper, Environment environment) {
        this.service = service;
        this.environment = environment;
        this.mapper = mapper;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ExchangeValueDto retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValueDto exchangeValueDto = mapper.toDto(service.findExchangeValueByFromAndTo(from, to));

        exchangeValueDto.setPort(
                Integer.parseInt(
                        Objects.requireNonNull(environment.getProperty("local.server.port"))));

        return exchangeValueDto;
    }


}
