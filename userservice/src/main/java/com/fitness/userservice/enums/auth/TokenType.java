package com.fitness.userservice.enums.auth;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TokenType {

    BEARER("Bearer");

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
