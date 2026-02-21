package com.fitness.activityservice.dto.activity.request;

import com.fitness.activityservice.enums.activity.ActivityType;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateActivityRequest(

        @NotNull
        @Positive
        Integer duration,

        @NotNull
        @Positive
        Integer caloriesBurned,

        @PastOrPresent
        LocalDateTime startTime,

        @NotNull
        ActivityType type,

        @Size(max = 20)
        Map<String, Object> additionalAttributes
) {
}
