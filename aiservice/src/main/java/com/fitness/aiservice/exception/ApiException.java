package com.fitness.aiservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(resolveMessage(message, status));
        this.status = resolveStatus(status);
    }

    private static String resolveMessage(String message, HttpStatus status) {
        HttpStatus resolvedStatus = resolveStatus(status);
        return (message == null || message.isBlank()) ? resolvedStatus.getReasonPhrase() : message;
    }

    private static HttpStatus resolveStatus(HttpStatus status) {
        return status != null ? status : HttpStatus.BAD_REQUEST;
    }
}
