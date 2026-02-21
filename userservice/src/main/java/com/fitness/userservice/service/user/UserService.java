package com.fitness.userservice.service.user;

import com.fitness.userservice.constants.message.user.UserMessages;
import com.fitness.userservice.dto.auth.request.RegisterUserRequest;
import com.fitness.userservice.dto.auth.response.UserResponse;
import com.fitness.userservice.entity.User;
import com.fitness.userservice.enums.auth.Role;
import com.fitness.userservice.exception.NotFoundException;
import com.fitness.userservice.mapper.user.UserMapper;
import com.fitness.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse register(RegisterUserRequest request) {

        return userRepository.findByEmail(request.email())
                .map(userMapper::toResponse
                )
                .orElseGet(() -> {
                    User user = new User(
                            request.email(),
                            request.firstName(),
                            request.lastName(),
                            request.keycloakId()
                    );
                    user.getRoles().add(Role.USER);
                    User createdUser = userRepository.save(user);

                    return userMapper.toResponse(createdUser);
                });
    }


    @Transactional(readOnly = true)
    public UserResponse getUserByKeycloakId(String keycloakId) {
        User user = userRepository.findByKeycloakId(keycloakId).orElseThrow(() -> new NotFoundException(UserMessages.USER_NOT_FOUND));

        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public Boolean userExistsByKeycloakId(String keycloakId) {
        return userRepository.existsByKeycloakId(keycloakId);
    }

}
