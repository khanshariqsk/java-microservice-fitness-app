package com.fitness.gateway.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.gateway.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> commence(ServerWebExchange exchange,
                               AuthenticationException ex) {

        var response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ApiResponse<Void> body = new ApiResponse<>(
                false,
                401,
                "Authentication failed",
                null
        );

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(body);
            return response.writeWith(
                    Mono.just(response.bufferFactory().wrap(bytes))
            );
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
