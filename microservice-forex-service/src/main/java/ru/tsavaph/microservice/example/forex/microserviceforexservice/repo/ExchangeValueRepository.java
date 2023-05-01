package ru.tsavaph.microservice.example.forex.microserviceforexservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;

import java.math.BigDecimal;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    @Query("SELECT AVG(e.conversionMultiple) FROM ExchangeValue e WHERE e.from = :from AND e.to = :to AND e.year = :year AND e.month = :month")
    BigDecimal findAvgConversionByFromToYearMonth(String from, String to, Integer year, Integer month);

    ExchangeValue findByFromAndToAndYearAndMonthAndDay(String from, String to, Integer year, Integer month, Integer day);
}
