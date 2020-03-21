package com.desafioJava.parlamentares.domain.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class DadosPublicException extends IntegrationException {

    public DadosPublicException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
