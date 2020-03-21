package com.desafioJava.parlamentares.domain.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class IntegrationException extends RuntimeException {
    @Getter
    private transient Map content;

    @Getter
    private HttpStatus httpStatus;

    public IntegrationException() {
        super();
    }

    public IntegrationException(String message) {
        super(message);
    }

    public IntegrationException(String message, HttpStatus httpStatus) {
        this(message, httpStatus, null);
    }

    public IntegrationException(String message, HttpStatus httpStatus, Map content) {
        super(message);
        this.httpStatus = httpStatus;
        this.content = content;
    }
}
