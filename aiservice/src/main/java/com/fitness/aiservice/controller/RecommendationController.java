package com.fitness.aiservice.controller;

import com.fitness.aiservice.constants.api.RecommendationPaths;
import com.fitness.aiservice.constants.message.recommendation.RecommendationMessages;
import com.fitness.aiservice.dto.common.ApiResponse;
import com.fitness.aiservice.dto.recommendation.response.RecommendationActivityResponse;
import com.fitness.aiservice.dto.recommendation.response.RecommendationResponse;
import com.fitness.aiservice.service.recommendation.RecommendationService;
import com.fitness.aiservice.util.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RecommendationPaths.BASE)
public class RecommendationController extends ApiResponseUtil {

    private final RecommendationService recommendationService;

    @GetMapping(RecommendationPaths.USER_RECOMMENDATIONS)
    public ResponseEntity<ApiResponse<List<RecommendationResponse>>> getUserRecommendations(@PathVariable String userId) {
        List<RecommendationResponse> recommendations = recommendationService.getUserRecommendations(userId);
        return ok(RecommendationMessages.USER_RECOMMENDATIONS_FETCHED, recommendations);
    }

    @GetMapping(RecommendationPaths.ACTIVITY_RECOMMENDATION)
    public ResponseEntity<ApiResponse<RecommendationActivityResponse>> getActivityRecommendation(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String activityId) {
        RecommendationActivityResponse recommendation = recommendationService.getActivityRecommendation(activityId, jwt.getTokenValue());
        return ok(RecommendationMessages.ACTIVITY_RECOMMENDATION_FETCHED, recommendation);
    }
}
