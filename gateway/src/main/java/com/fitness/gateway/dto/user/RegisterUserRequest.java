package com.fitness.gateway.dto.user;

public record RegisterUserRequest(
        String email,

        String firstName,

        String lastName,

        String keycloakId
) {
}
