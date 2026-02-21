package com.fitness.gateway.config.webflux;

import com.fitness.gateway.service.user.UserPropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final UserPropertiesService userPropertiesService;

    @Bean
    @LoadBalanced
    public WebClient.Builder webBuilderClient() {
        return WebClient.builder();
    }

    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webBuilderClient) {
        return webBuilderClient.baseUrl(userPropertiesService.baseUrl()).build();
    }
}
