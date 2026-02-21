package com.fitness.gateway.service.user;

import com.fitness.gateway.dto.user.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserService {

    private final WebClient userServiceWebClient;
    private final UserPropertiesService userPropertiesService;

    public Mono<Void> syncUser(RegisterUserRequest request) {

        String uri = userPropertiesService.basePathAuth() + userPropertiesService.endpoints().register();

        return userServiceWebClient.post()
                .uri(uri)
                .bodyValue(request)
                .retrieve()

                // Do NOT throw exception for 4xx / 5xx
                .onStatus(
                        HttpStatusCode::isError,
                        response -> Mono.empty()
                )

                // Expect no body
                .bodyToMono(Void.class)

                // Timeout safety
                .timeout(Duration.ofSeconds(2))

                // If anything fails, just continue silently
                .onErrorResume(ex -> Mono.empty());
    }

}
