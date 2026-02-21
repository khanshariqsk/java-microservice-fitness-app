package com.fitness.userservice.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank()
        @Email()
        String email,

        @NotBlank()
        String firstName,

        @NotBlank()
        String lastName,

        @NotBlank()
        String keycloakId
) {
}
