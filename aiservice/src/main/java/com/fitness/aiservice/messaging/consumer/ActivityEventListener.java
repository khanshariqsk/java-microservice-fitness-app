package com.fitness.aiservice.messaging.consumer;

import com.fitness.aiservice.entity.Recommendation;
import com.fitness.aiservice.messaging.event.ActivityCreatedEvent;
import com.fitness.aiservice.service.ai.ActivityAiService;
import com.fitness.aiservice.service.recommendation.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityEventListener {

    private final RecommendationService recommendationService;
    private final ActivityAiService activityAiService;

    @KafkaListener(
            topics = "${app.kafka.topics.activity-events}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ActivityCreatedEvent event) {
        try {
            Recommendation recommendation = activityAiService.generateRecommendation(event);
            recommendationService.createRecommendation(recommendation);
        } catch (Exception ex) {
            System.err.println("Gemini failed: " + ex.getMessage());
        }
    }
}
