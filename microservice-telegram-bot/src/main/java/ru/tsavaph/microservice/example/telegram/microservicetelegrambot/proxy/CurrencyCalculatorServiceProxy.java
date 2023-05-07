package ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bean.CurrencyConversionBean;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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


    default int countRetrieveAvgExchangeValuePathVariables() {
        int countPathVariables;
        try {
            countPathVariables = countPathVariables(CurrencyCalculatorServiceProxy.class.getMethod(
                    "retrieveAvgExchangeValue",
                    String.class,
                    String.class,
                    Integer.class,
                    Integer.class,
                    BigDecimal.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return countPathVariables;
    }

    default int countRetrieveExchangeValuePathVariables() {
        int countPathVariables;
        try {
            countPathVariables = countPathVariables(CurrencyCalculatorServiceProxy.class.getMethod(
                    "retrieveExchangeValue",
                    String.class,
                    String.class,
                    Integer.class,
                    Integer.class,
                    Integer.class,
                    BigDecimal.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return countPathVariables;
    }

    private int countPathVariables(Method method) {
        int count = 0;
        for (Annotation[] annotations : method.getParameterAnnotations()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof PathVariable) {
                    count++;
                }
            }
        }
        return count;
    }
}
