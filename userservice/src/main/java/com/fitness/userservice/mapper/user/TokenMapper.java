package com.fitness.userservice.mapper.user;


import com.fitness.userservice.dto.auth.response.TokenResponse;
import com.fitness.userservice.enums.auth.TokenType;
import org.springframework.stereotype.Component;

@Component
public class TokenMapper {
    public TokenResponse toResponse(String accessToken, TokenType tokenType, Long expiresIn) {
        return new TokenResponse(accessToken, tokenType, expiresIn);
    }
}
