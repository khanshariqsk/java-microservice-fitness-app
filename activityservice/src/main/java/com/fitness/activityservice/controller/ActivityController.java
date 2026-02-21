package com.fitness.activityservice.controller;

import com.fitness.activityservice.constants.api.ActivityPaths;
import com.fitness.activityservice.constants.message.activity.ActivityMessages;
import com.fitness.activityservice.dto.activity.request.CreateActivityRequest;
import com.fitness.activityservice.dto.activity.response.ActivityResponse;
import com.fitness.activityservice.dto.common.ApiResponse;
import com.fitness.activityservice.service.activity.ActivityService;
import com.fitness.activityservice.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ActivityPaths.BASE)
public class ActivityController extends ApiResponseUtil {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ApiResponse<ActivityResponse>> create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateActivityRequest request) {

        String userId = jwt.getSubject();

        ActivityResponse activity = activityService.createActivity(request, userId);
        return created(ActivityMessages.ACTIVITY_CREATED, activity);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ActivityResponse>>> getAll() {
        List<ActivityResponse> activities = activityService.getActivities();
        return ok(ActivityMessages.ACTIVITIES_FETCHED, activities);
    }

    @GetMapping(ActivityPaths.BY_ID)
    public ResponseEntity<ApiResponse<ActivityResponse>> getActivityById(@PathVariable String activityId) {
        ActivityResponse activity = activityService.getActivityById(activityId);
        return ok(ActivityMessages.ACTIVITY_FETCHED, activity);
    }
}
