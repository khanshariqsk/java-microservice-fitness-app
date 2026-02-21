package com.fitness.aiservice.integration.gemini;

import com.fitness.aiservice.config.app.AppProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
public class GeminiService {
    private final WebClient webClient;

    private final String geminiApiUrl;
    private final String geminiApiKey;

    public GeminiService(WebClient.Builder webBuilderClient, AppProperties appProperties) {
        this.webClient = webBuilderClient.build();

        this.geminiApiKey = appProperties.getGemini().getApiKey();
        this.geminiApiUrl = appProperties.getGemini().getApiUrl();
    }

    public String getRecommendation(String details) {

        Map<String, Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of("text", details)
                                }
                        )
                }
        );

        return webClient.post()
                .uri(geminiApiUrl + "?key=" + geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

}
