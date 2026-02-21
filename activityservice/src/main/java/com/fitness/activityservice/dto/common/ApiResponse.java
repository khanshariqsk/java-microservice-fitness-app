package com.fitness.activityservice.dto.common;

public record ApiResponse<T>(
        boolean success,
        int statusCode,
        String message,
        T data
) {
}
