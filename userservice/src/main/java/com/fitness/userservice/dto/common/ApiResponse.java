package com.fitness.userservice.dto.common;

public record ApiResponse<T>(
        boolean success,
        int statusCode,
        String message,
        T data
) {
}
