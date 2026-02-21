package com.fitness.aiservice.dto.recommendation.response;

import java.time.LocalDateTime;
import java.util.List;

public record RecommendationResponse(

        String id,
        String activityId,
        String userId,

        String recommendation,

        List<String> improvements,
        List<String> suggestions,
        List<String> safety,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
