package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.bean.CurrencyConversionBean;

@FeignClient(name = "forex-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
