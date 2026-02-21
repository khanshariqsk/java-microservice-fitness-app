package com.fitness.aiservice.dto.activity.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ActivityResponse(String id, Integer duration, Integer caloriesBurned,LocalDateTime startTime,String type,Map<String, Object> additionalAttributes,String userId,LocalDateTime createdAt,LocalDateTime updatedAt) { }
