package ru.tsavaph.microservice.example.forex.microserviceforexservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.dto.ExchangeValueDto;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.mapper.ExchangeValueMapper;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.service.ForexService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping(path = "/currency-exchange")
public class ForexController {
    private final ExchangeValueMapper mapper;

    private final ForexService service;

    @Autowired
    public ForexController(ForexService service, ExchangeValueMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/from/{from}/to/{to}/year/{year}/month/{month}")
    public ExchangeValueDto retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable Integer year,
            @PathVariable Integer month) {

        BigDecimal conversion = service.findAngConversionByFromToYearAndMonth(from, to, year, month);

        return new ExchangeValueDto(from, to, year, month, conversion.setScale(4, RoundingMode.HALF_UP));
    }


}
