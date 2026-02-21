package com.fitness.userservice.dto.auth.response;

public record TokenUserResponse(TokenResponse token, UserResponse user) {
}