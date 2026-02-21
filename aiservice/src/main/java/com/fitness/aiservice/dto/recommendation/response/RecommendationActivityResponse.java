package com.fitness.aiservice.dto.recommendation.response;

import com.fitness.aiservice.dto.activity.response.ActivityResponse;

import java.time.LocalDateTime;
import java.util.List;

public record RecommendationActivityResponse(

        String id,
        String activityId,
        String userId,

        String recommendation,
        ActivityResponse activity,

        List<String> improvements,
        List<String> suggestions,
        List<String> safety,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
