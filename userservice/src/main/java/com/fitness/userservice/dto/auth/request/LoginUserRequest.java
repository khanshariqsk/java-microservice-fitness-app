package com.fitness.userservice.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserRequest(
        @NotBlank()
        @Email()
        String email,

        @NotBlank()
        @Size(min = 5, max = 20)
        String password
) {
}
