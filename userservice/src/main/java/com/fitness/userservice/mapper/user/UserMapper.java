package com.fitness.userservice.mapper.user;


import com.fitness.userservice.dto.auth.response.UserResponse;
import com.fitness.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getRoles(),user.getKeycloakId());
    }
}
