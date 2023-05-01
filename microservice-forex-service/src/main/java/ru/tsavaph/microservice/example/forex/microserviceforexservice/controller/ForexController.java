package ru.tsavaph.microservice.example.forex.microserviceforexservice.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.dto.ExchangeValueDto;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.mapper.ExchangeValueMapper;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.service.ForexService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@Validated
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
    public ExchangeValueDto retrieveAvgExchangeValue(
            @PathVariable @Length(min = 3, max = 3) String from,
            @PathVariable @Length(min = 3, max = 3) String to,
            @PathVariable @Min(1900) @Max(2100) Integer year,
            @PathVariable @Min(1) @Max(12) Integer month) {

        from = from.toUpperCase();
        to = to.toUpperCase();

        BigDecimal conversion = service.findAvgConversionByFromToYearAndMonth(from, to, year, month);

        return new ExchangeValueDto(from, to, year, month, conversion.setScale(4, RoundingMode.HALF_UP));
    }

    @GetMapping("/from/{from}/to/{to}/year/{year}/month/{month}/day/{day}")
    public ExchangeValueDto retrieveExchangeValue(
            @PathVariable @Length(min = 3, max = 3) String from,
            @PathVariable @Length(min = 3, max = 3) String to,
            @PathVariable @Min(1900) @Max(2100) Integer year,
            @PathVariable @Min(1) @Max(12) Integer month,
            @PathVariable @Min(1) @Max(31) Integer day) {

        from = from.toUpperCase();
        to = to.toUpperCase();

        ExchangeValue exchangeValue = service.findConversionByFromToYearAndMonthAndDay(from, to, year, month, day);

        return mapper.toDto(exchangeValue);
    }


}
