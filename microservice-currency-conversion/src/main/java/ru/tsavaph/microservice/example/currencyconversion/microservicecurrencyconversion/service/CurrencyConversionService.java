package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.bean.CurrencyConversionBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CurrencyConversionService {
    public CurrencyConversionBean makeCurrencyConversionBean(String from, String to, BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
                uriVariables);

        CurrencyConversionBean response = responseEntity.getBody();

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
