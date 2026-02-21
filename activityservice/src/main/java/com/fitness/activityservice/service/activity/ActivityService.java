package com.fitness.activityservice.service.activity;

import com.fitness.activityservice.constants.message.activity.ActivityMessages;
import com.fitness.activityservice.constants.message.user.UserMessages;
import com.fitness.activityservice.dto.activity.request.CreateActivityRequest;
import com.fitness.activityservice.dto.activity.response.ActivityResponse;
import com.fitness.activityservice.entity.Activity;
import com.fitness.activityservice.exception.NotFoundException;
import com.fitness.activityservice.mapper.activity.ActivityMapper;
import com.fitness.activityservice.mapper.event.ActivityEventMapper;
import com.fitness.activityservice.messaging.publisher.ActivityEventPublisher;
import com.fitness.activityservice.repository.ActivityRepository;
import com.fitness.activityservice.service.user.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;
    private final ActivityEventMapper activityEventMapper;
    private final UserValidationService userValidationService;
    private final ActivityEventPublisher activityEventPublisher;


    @Transactional
    public ActivityResponse createActivity(CreateActivityRequest request, String userId) {

        boolean userExists = userValidationService.checkIfUserExists(userId);

        if (!userExists) {
            throw new NotFoundException(UserMessages.USER_NOT_FOUND);
        }

        LocalDateTime startTime = request.startTime() != null
                ? request.startTime()
                : LocalDateTime.now();

        Activity activity = new Activity(request.duration(), request.caloriesBurned(), startTime, request.type(), request.additionalAttributes(), userId);

        Activity createdActivity = activityRepository.save(activity);

        activityEventPublisher.publishActivityCreated(activityEventMapper.toCreatedEvent(createdActivity));

        return activityMapper.toResponse(createdActivity);
    }

    @Transactional(readOnly = true)
    public List<ActivityResponse> getActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(activityMapper::toResponse).toList();
    }

    public ActivityResponse getActivityById(String activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NotFoundException(ActivityMessages.ACTIVITY_NOT_FOUND));

        return activityMapper.toResponse(activity);
    }
}
