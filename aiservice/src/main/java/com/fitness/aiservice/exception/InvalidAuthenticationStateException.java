package com.fitness.aiservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidAuthenticationStateException extends ApiException {
    public InvalidAuthenticationStateException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}