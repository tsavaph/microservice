package ru.tsavaph.microservice.example.forex.microserviceforexservice.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

}
