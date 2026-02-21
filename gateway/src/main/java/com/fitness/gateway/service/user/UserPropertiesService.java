package com.fitness.gateway.service.user;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "services.userservice")
public record UserPropertiesService(
        String baseUrl,
        String basePathAuth,
        String basePathUsers,
        Endpoints endpoints
) {

    public record Endpoints(
            String exists,
            String findById,
            String register
    ) {
    }
}
