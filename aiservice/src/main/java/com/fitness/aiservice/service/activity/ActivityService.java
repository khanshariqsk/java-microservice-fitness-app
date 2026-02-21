package com.fitness.aiservice.service.activity;

import com.fitness.aiservice.constants.message.activity.ActivityMessages;
import com.fitness.aiservice.dto.activity.response.ActivityResponse;
import com.fitness.aiservice.dto.common.ApiResponse;
import com.fitness.aiservice.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityPropertiesService activityPropertiesService;
    private final WebClient activityServiceWebClient;

    public ActivityResponse getActivityById(String activityId, String token) {

        String uri = activityPropertiesService.basePath()
                + activityPropertiesService.endpoints().findById();

        return activityServiceWebClient.get()
                .uri(uri, activityId)

                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()


                .onStatus(
                        status -> status.value() == 404,
                        clientResponse -> Mono.error(
                                new NotFoundException(ActivityMessages.ACTIVITY_NOT_FOUND)
                        )
                )

                .onStatus(
                        HttpStatusCode::isError,
                        clientResponse -> Mono.error(
                                new RuntimeException("Activity service error")
                        )
                )
                // Deserialize wrapped response
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<ActivityResponse>>() {
                })

                // Extract actual activity
                .map(ApiResponse::data)

                // Timeout safety
                .timeout(Duration.ofSeconds(2))

                // If anything fails → return null (or throw custom exception)
                .onErrorResume(ex -> Mono.empty())

                // Convert reactive → blocking (MVC boundary)
                .block();
    }

}
