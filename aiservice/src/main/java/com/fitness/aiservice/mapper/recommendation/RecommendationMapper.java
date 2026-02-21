package com.fitness.aiservice.mapper.recommendation;

import com.fitness.aiservice.dto.activity.response.ActivityResponse;
import com.fitness.aiservice.dto.recommendation.response.RecommendationActivityResponse;
import com.fitness.aiservice.dto.recommendation.response.RecommendationResponse;
import com.fitness.aiservice.entity.Recommendation;
import org.springframework.stereotype.Component;

@Component
public class RecommendationMapper {

    public RecommendationResponse toResponse(Recommendation recommendation) {

        return new RecommendationResponse(
                recommendation.getId(),
                recommendation.getActivityId(),
                recommendation.getUserId(),
                recommendation.getRecommendation(),
                recommendation.getImprovements(),
                recommendation.getSuggestions(),
                recommendation.getSafety(),
                recommendation.getCreatedAt(),
                recommendation.getUpdatedAt()
        );
    }

    public RecommendationActivityResponse toResponseWithActivity(Recommendation recommendation, ActivityResponse activityResponse) {

        return new RecommendationActivityResponse(
                recommendation.getId(),
                recommendation.getActivityId(),
                recommendation.getUserId(),
                recommendation.getRecommendation(),
                activityResponse,
                recommendation.getImprovements(),
                recommendation.getSuggestions(),
                recommendation.getSafety(),
                recommendation.getCreatedAt(),
                recommendation.getUpdatedAt()
        );
    }
}
