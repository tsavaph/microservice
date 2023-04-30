package ru.tsavaph.microservice.example.forex.microserviceforexservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndToAndYearAndMonth(String from, String to, Integer year, Integer month);
}
