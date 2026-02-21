package com.fitness.gateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.gateway.dto.ApiResponse;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.net.ConnectException;

@Component
@Order(-2)
public class GlobalGatewayExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        HttpStatus status = resolveStatus(ex);

        ApiResponse<Void> body = new ApiResponse<>(
                false,
                status.value(),
                resolveMessage(status),
                null
        );

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(body);
            return response.writeWith(
                    Mono.just(response.bufferFactory().wrap(bytes))
            );
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    private HttpStatus resolveStatus(Throwable ex) {

        if (ex instanceof ResponseStatusException rse) {
            return HttpStatus.valueOf(rse.getStatusCode().value());
        }

        if (ex instanceof ConnectException) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String resolveMessage(HttpStatus status) {
        return switch (status) {
            case NOT_FOUND -> "Route not found";
            case METHOD_NOT_ALLOWED -> "Method not allowed";
            case SERVICE_UNAVAILABLE -> "Service unavailable";
            case UNAUTHORIZED -> "Authentication failed";
            default -> "Internal server error";
        };
    }
}
