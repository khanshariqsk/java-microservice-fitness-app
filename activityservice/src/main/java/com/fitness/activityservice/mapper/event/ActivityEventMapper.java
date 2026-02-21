package com.fitness.activityservice.mapper.event;

import com.fitness.activityservice.entity.Activity;
import com.fitness.activityservice.messaging.event.ActivityCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class ActivityEventMapper {

    public ActivityCreatedEvent toCreatedEvent(Activity activity) {
        return new ActivityCreatedEvent(
                activity.getId(),
                activity.getUserId(),
                activity.getType().name(),
                activity.getDuration(),
                activity.getCaloriesBurned(),
                activity.getStartTime(),
                activity.getAdditionalMetrics(),
                activity.getCreatedAt()
        );
    }
}
