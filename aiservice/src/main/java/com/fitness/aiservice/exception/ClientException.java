package com.fitness.aiservice.exception;

import org.springframework.http.HttpStatus;

public class ClientException extends ApiException {
    public ClientException(String message, HttpStatus status) {
        super(message, status);
    }
}
