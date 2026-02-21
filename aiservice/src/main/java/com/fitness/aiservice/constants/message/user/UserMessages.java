package com.fitness.aiservice.constants.message.user;

import com.fitness.aiservice.constants.message.common.CommonMessages;

public class UserMessages {
    private static final String BASE_NAME = UserEntityNames.USER;

    private UserMessages() {
    }

    //  <========================== Success Messages ===============================>
    public static final String USER_CREATED = BASE_NAME + " " + CommonMessages.CREATED_SUCCESSFULLY;
    public static final String USER_FETCHED = BASE_NAME + " " + CommonMessages.FETCHED_SUCCESSFULLY;

    public static final String USER_LOGGED_IN_SUCCESSFULLY =

            BASE_NAME + " " + CommonMessages.LOGGED_IN_SUCCESSFULLY;

    //  <========================== Error Messages ===============================>
    public static final String USER_NOT_FOUND =
            BASE_NAME + " " + CommonMessages.NOT_FOUND;

    public static final String USER_ALREADY_EXISTS =
            BASE_NAME + " " + CommonMessages.ALREADY_EXISTS;
}
