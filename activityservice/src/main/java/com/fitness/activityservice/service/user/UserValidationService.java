package com.fitness.activityservice.service.user;

import com.fitness.activityservice.dto.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final WebClient userServiceWebClient;
    private final UserPropertiesService userPropertiesService;

    public boolean checkIfUserExists(String userId) {

        String uri = userPropertiesService.basePathUsers() + userPropertiesService.endpoints().exists();

        return Boolean.TRUE.equals(userServiceWebClient.get()
                .uri(uri, userId)
                .retrieve()

                // 1️⃣ Do NOT throw exception for 4xx / 5xx
                .onStatus(
                        HttpStatusCode::isError,
                        response -> Mono.empty()
                )

                // 2️⃣ Deserialize wrapped response
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<Boolean>>() {
                })

                // 3️⃣ Extract actual boolean
                .map(ApiResponse::data)

                // 4️⃣ Never wait forever
                .timeout(Duration.ofSeconds(2))

                // 5️⃣ Any failure = user does not exist
                .onErrorReturn(false)

                // 6️⃣ Convert async → sync (MVC boundary)
                .block());
    }


}
