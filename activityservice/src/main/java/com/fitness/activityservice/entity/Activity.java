package com.fitness.activityservice.entity;

import com.fitness.activityservice.constants.message.activity.ActivityEntityNames;
import com.fitness.activityservice.enums.activity.ActivityType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = ActivityEntityNames.ACTIVITIES_LC)
public class Activity {

    public Activity(Integer duration, Integer caloriesBurned, LocalDateTime startTime, ActivityType type, Map<String, Object> additionalMetrics, String userId) {
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.startTime = startTime;
        this.type = type;
        this.additionalMetrics = additionalMetrics;
        this.userId = userId;
    }

    @Id
    private String id;
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;

    @Field("metrics")
    private Map<String, Object> additionalMetrics;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}


