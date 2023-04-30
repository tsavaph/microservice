package ru.tsavaph.microservice.example.forex.microserviceforexservice.mapper;

import org.mapstruct.Mapper;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.dto.ExchangeValueDto;
import ru.tsavaph.microservice.example.forex.microserviceforexservice.entity.ExchangeValue;

@Mapper(componentModel = "spring")
public interface ExchangeValueMapper {
    ExchangeValueDto toDto(ExchangeValue entity);
}
