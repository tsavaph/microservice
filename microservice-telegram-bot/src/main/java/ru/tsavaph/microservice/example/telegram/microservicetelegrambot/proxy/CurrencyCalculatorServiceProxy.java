package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bean.CurrencyConversionBean;

import java.math.BigDecimal;

@FeignClient(name = "currency-conversion-service")
public interface CurrencyCalculatorServiceProxy {

    @GetMapping("/currency-converter/from/{from}/to/{to}/year/{year}/month/{month}/quantity/{quantity}")
    CurrencyConversionBean retrieveAvgExchangeValue(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("quantity") BigDecimal quantity);

    @GetMapping("/currency-converter/from/{from}/to/{to}/year/{year}/month/{month}/day/{day}/quantity/{quantity}")
    CurrencyConversionBean retrieveExchangeValue(
            @PathVariable("from") String from,
            @PathVariable("to") String to,
            @PathVariable("year") Integer year,
            @PathVariable("month") Integer month,
            @PathVariable("day") Integer day,
            @PathVariable("quantity") BigDecimal quantity);
}
