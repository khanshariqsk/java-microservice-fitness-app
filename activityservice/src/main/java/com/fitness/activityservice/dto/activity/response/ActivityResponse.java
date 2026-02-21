package com.fitness.activityservice.dto.activity.response;

import com.fitness.activityservice.enums.activity.ActivityType;

import java.time.LocalDateTime;
import java.util.Map;

public record ActivityResponse(String id, Integer duration, Integer caloriesBurned,LocalDateTime startTime,ActivityType type,Map<String, Object> additionalAttributes,String userId,LocalDateTime createdAt,LocalDateTime updatedAt) { }
