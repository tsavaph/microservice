package ru.tsavaph.microservice.example.forex.microserviceforexservice.exception;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
    }

    public InvalidDateException(String message) {
        super(message);
    }

}
