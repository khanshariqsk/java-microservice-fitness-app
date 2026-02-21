package com.fitness.aiservice.config.webflux;

import com.fitness.aiservice.service.activity.ActivityPropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ActivityPropertiesService activityPropertiesService;

    @Bean
    @LoadBalanced
    public WebClient.Builder webBuilderClient() {
        return WebClient.builder();
    }

    @Bean
    public WebClient activityServiceWebClient(WebClient.Builder webBuilderClient) {
        return webBuilderClient.baseUrl(activityPropertiesService.baseUrl()).build();
    }

}
