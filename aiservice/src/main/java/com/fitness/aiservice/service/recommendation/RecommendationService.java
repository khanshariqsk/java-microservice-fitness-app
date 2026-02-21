package com.fitness.aiservice.service.recommendation;

import com.fitness.aiservice.constants.message.recommendation.RecommendationMessages;
import com.fitness.aiservice.dto.activity.response.ActivityResponse;
import com.fitness.aiservice.dto.recommendation.response.RecommendationActivityResponse;
import com.fitness.aiservice.dto.recommendation.response.RecommendationResponse;
import com.fitness.aiservice.entity.Recommendation;
import com.fitness.aiservice.exception.NotFoundException;
import com.fitness.aiservice.mapper.recommendation.RecommendationMapper;
import com.fitness.aiservice.repository.RecommendationRepository;
import com.fitness.aiservice.service.activity.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final RecommendationMapper recommendationMapper;
    private final ActivityService activityService;

    @Transactional(readOnly = true)
    public List<RecommendationResponse> getUserRecommendations(String userId) {
        List<Recommendation> recommendations = recommendationRepository.findByUserId(userId);
        return recommendations.stream().map(recommendationMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public RecommendationActivityResponse getActivityRecommendation(String activityId, String token) {

        ActivityResponse activityResponse = activityService.getActivityById(activityId, token);

        Recommendation recommendation = recommendationRepository.findByActivityId(activityId).orElseThrow(() -> new NotFoundException(RecommendationMessages.ACTIVITY_RECOMMENDATION_NOT_FOUND));

        return recommendationMapper.toResponseWithActivity(recommendation, activityResponse);
    }

    public void createRecommendation(Recommendation recommendation) {
        recommendationRepository.save(recommendation);
    }
}
