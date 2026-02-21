package com.fitness.aiservice.exception;

import org.springframework.http.HttpStatus;

public class UnauthenticatedException extends ApiException {
    public UnauthenticatedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
