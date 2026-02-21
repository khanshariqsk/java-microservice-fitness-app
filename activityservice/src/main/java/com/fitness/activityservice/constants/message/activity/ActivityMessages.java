package com.fitness.activityservice.constants.message.activity;

import com.fitness.activityservice.constants.message.common.CommonMessages;

public final class ActivityMessages {
    private static final String BASE_NAME = ActivityEntityNames.ACTIVITY;
    private static final String BASE_NAME_PLURAL = ActivityEntityNames.ACTIVITIES;

    private ActivityMessages() {
    }

    //  <========================== Success Messages ===============================>
    public static final String ACTIVITY_CREATED = BASE_NAME + " " + CommonMessages.CREATED_SUCCESSFULLY;

    public static final String ACTIVITY_FETCHED = BASE_NAME + " " + CommonMessages.FETCHED_SUCCESSFULLY;
    public static final String ACTIVITIES_FETCHED = BASE_NAME_PLURAL + " " + CommonMessages.FETCHED_SUCCESSFULLY;

    //  <========================== Error Messages ===============================>
    public static final String ACTIVITY_NOT_FOUND = BASE_NAME + " " + CommonMessages.NOT_FOUND;
}
