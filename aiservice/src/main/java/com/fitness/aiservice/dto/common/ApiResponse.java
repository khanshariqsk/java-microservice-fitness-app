package com.fitness.aiservice.dto.common;

public record ApiResponse<T>(
        boolean success,
        int statusCode,
        String message,
        T data
) {
}
