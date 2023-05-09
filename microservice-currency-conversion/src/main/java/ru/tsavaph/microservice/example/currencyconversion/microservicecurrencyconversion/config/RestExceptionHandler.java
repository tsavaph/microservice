package ru.tsavaph.microservice.example.currencyconversion.microservicecurrencyconversion.config;

import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {

    private record ExceptionResponse(List<String> errors) {
    }

    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<String> notFound(final FeignException ex) {
        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatusCode.valueOf(ex.status()));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequest(final ConstraintViolationException ex) {
        return new ExceptionResponse(List.of(ex.getMessage()));
    }

}
