package com.fitness.aiservice.util;

import com.fitness.aiservice.dto.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ApiResponseUtil {
    private String resolveMessage(String message, HttpStatus status) {
        return (message == null || message.isBlank())
                ? status.getReasonPhrase()
                : message;
    }

    private HttpStatus resolveStatus(HttpStatus status) {
        return status != null ? status : HttpStatus.OK;
    }

    protected <T> ResponseEntity<ApiResponse<T>> success(String message, HttpStatus status, T data) {
        HttpStatus resolvedStatus = resolveStatus(status);
        String resolvedMessage = resolveMessage(message, resolvedStatus);

        return ResponseEntity.status(resolvedStatus).body(new ApiResponse<T>(true, resolvedStatus.value(), resolvedMessage, data));
    }

    protected ResponseEntity<ApiResponse<Void>> noContent(String message) {
        return success(message, HttpStatus.NO_CONTENT, null);
    }

    protected <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return success(message, HttpStatus.OK, data);
    }

    protected <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return success(message, HttpStatus.CREATED, data);
    }

    protected <T> ResponseEntity<ApiResponse<T>> accepted(String message, T data) {
        return success(message, HttpStatus.ACCEPTED, data);
    }
}
