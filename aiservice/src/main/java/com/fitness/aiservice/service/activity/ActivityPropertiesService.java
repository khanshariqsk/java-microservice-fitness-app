package com.fitness.aiservice.service.activity;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "services.activityservice")
public record ActivityPropertiesService(
        String baseUrl,
        String basePath,
        Endpoints endpoints
) {

    public record Endpoints(
            String findById
    ) {
    }
}
