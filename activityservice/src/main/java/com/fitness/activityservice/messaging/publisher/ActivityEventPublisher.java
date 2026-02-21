package com.fitness.activityservice.messaging.publisher;

import com.fitness.activityservice.config.app.AppProperties;
import com.fitness.activityservice.messaging.event.ActivityCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityEventPublisher {

    private final KafkaTemplate<String, ActivityCreatedEvent> kafkaTemplate;
    private final AppProperties appProperties;

    public void publishActivityCreated(ActivityCreatedEvent event) {
        String topic = appProperties.getKafka()
                .getTopics()
                .getActivityEvents();

        kafkaTemplate.send(topic, event.activityId(), event);
    }
}
