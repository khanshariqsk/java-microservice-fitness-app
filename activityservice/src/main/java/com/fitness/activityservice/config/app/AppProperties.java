package com.fitness.activityservice.config.app;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {
    private boolean debugErrors;

    private Kafka kafka;

    @Getter
    @Setter
    public static class Kafka {
        private Topics topics;
    }

    @Getter
    @Setter
    public static class Topics {
        private String activityEvents;
    }
}
