package com.fitness.activityservice.mapper.activity;


import com.fitness.activityservice.dto.activity.response.ActivityResponse;
import com.fitness.activityservice.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {
    public ActivityResponse toResponse(Activity activity) {
        return new ActivityResponse(activity.getId(), activity.getDuration(), activity.getCaloriesBurned(), activity.getStartTime(), activity.getType(), activity.getAdditionalMetrics(), activity.getUserId(), activity.getCreatedAt(), activity.getUpdatedAt());
    }
}
