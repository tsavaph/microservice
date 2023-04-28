package ru.tsavaph.microservice.example.forex.microserviceforexservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.service.ForexService;

@RestController
@RequestMapping(path = "/currency-exchange")
public class ForexController {

    private final ForexService service;

    @Autowired
    public ForexController(ForexService service) {
        this.service = service;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        return service.findExchangeValueByFromAndTo(from, to);
    }


}
