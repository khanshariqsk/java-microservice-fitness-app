package com.fitness.userservice.dto.auth.response;

import com.fitness.userservice.enums.auth.Role;

import java.util.List;

public record UserResponse(String id, String email, String firstName, String lastName, List<Role> roles,String keycloakId) {
}