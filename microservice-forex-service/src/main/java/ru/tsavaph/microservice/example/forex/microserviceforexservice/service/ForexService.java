package ru.tsavaph.microservice.example.forex.microserviceforexservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.exception.InvalidDateException;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.exception.NotFoundException;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.repo.ExchangeValueRepository;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;

@Service
public class ForexService {
    private final ExchangeValueRepository repository;

    @Autowired
    public ForexService(ExchangeValueRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BigDecimal findAvgConversionByFromToYearAndMonth(String from, String to, Integer year, Integer month) {

        BigDecimal avgConversion = repository.findAvgConversionByFromToYearMonth(from, to, year, month);

        if (avgConversion == null) {
            throw new NotFoundException(String.format(
                    "There no information for from = %s, to = %s, year = %d, month = %d",
                    from, to, year, month
            ));
        }

        return avgConversion;
    }

    @Transactional
    public ExchangeValue findConversionByFromToYearAndMonthAndDay(String from, String to, Integer year, Integer month, Integer day) {

        checkDate(year, month, day);

        ExchangeValue exchangeValue = repository.findByFromAndToAndYearAndMonthAndDay(from, to, year, month, day);

        if (exchangeValue == null) {
            throw new NotFoundException(String.format(
                    "There no information for from = %s, to = %s, year = %d, month = %d, day = %d",
                    from, to, year, month, day
            ));
        }

        return exchangeValue;
    }

    private void checkDate(Integer year, Integer month, Integer day) {
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }
}
