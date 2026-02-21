package com.fitness.aiservice.exception;

import org.springframework.http.HttpStatus;

public class ServerException extends ApiException {
    public ServerException(String message, HttpStatus status) {
        super(message, status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
