package com.fitness.userservice.controller;

import com.fitness.userservice.constants.api.AuthPaths;
import com.fitness.userservice.constants.message.user.UserMessages;
import com.fitness.userservice.dto.auth.request.LoginUserRequest;
import com.fitness.userservice.dto.auth.request.RegisterUserRequest;
import com.fitness.userservice.dto.auth.response.TokenUserResponse;
import com.fitness.userservice.dto.auth.response.UserResponse;
import com.fitness.userservice.dto.common.ApiResponse;
import com.fitness.userservice.service.user.UserService;
import com.fitness.userservice.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthPaths.BASE)
public class AuthController extends ApiResponseUtil {

    private final UserService userService;

    @PostMapping(AuthPaths.REGISTER)
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody RegisterUserRequest request) {
        UserResponse user = userService.register(request);
        return created(UserMessages.USER_CREATED, user);
    }

}
