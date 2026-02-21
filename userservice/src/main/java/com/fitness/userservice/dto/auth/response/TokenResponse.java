package com.fitness.userservice.dto.auth.response;

import com.fitness.userservice.enums.auth.TokenType;

public record TokenResponse(String accessToken, TokenType tokenType, Long expiresIn) {
}