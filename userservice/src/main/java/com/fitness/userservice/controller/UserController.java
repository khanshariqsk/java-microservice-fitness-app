package com.fitness.userservice.controller;

import com.fitness.userservice.constants.api.UserPaths;
import com.fitness.userservice.constants.message.user.UserMessages;
import com.fitness.userservice.dto.auth.response.UserResponse;
import com.fitness.userservice.dto.common.ApiResponse;
import com.fitness.userservice.service.user.UserService;
import com.fitness.userservice.util.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserPaths.BASE)
public class UserController extends ApiResponseUtil {

    private final UserService userService;

    @GetMapping(UserPaths.BY_ID)
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable String keycloakId) {
        UserResponse user = userService.getUserByKeycloakId(keycloakId);
        return ok(UserMessages.USER_FETCHED, user);
    }

    @GetMapping(UserPaths.USER_EXISTS)
    public ResponseEntity<ApiResponse<Boolean>> existsByKeycloakId(@PathVariable String keycloakId) {
        return ok(UserMessages.USER_EXISTS, userService.userExistsByKeycloakId(keycloakId));
    }
}
