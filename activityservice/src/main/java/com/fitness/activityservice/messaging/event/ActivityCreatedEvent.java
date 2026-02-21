package com.fitness.activityservice.messaging.event;
import java.time.LocalDateTime;
import java.util.Map;

public record ActivityCreatedEvent(
        String activityId,
        String userId,
        String type,
        Integer duration,
        Integer caloriesBurned,
        LocalDateTime startTime,
        Map<String, Object> additionalMetrics,
        LocalDateTime createdAt
) {
}
