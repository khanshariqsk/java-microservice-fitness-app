package com.fitness.aiservice.constants.api;

public final class RecommendationPaths {
    public final static String BASE = ApiPaths.API_V1 + "/recommendations";

    public final static String USER_RECOMMENDATIONS = "/user/{userId}";
    public final static String ACTIVITY_RECOMMENDATION = "/activity/{activityId}";
}
