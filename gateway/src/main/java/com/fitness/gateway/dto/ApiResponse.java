package com.fitness.gateway.dto;

public record ApiResponse<T>(
        boolean success,
        int statusCode,
        String message,
        T data
) {
}
