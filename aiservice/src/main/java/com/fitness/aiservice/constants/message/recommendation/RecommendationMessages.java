package com.fitness.aiservice.constants.message.recommendation;

import com.fitness.aiservice.constants.message.activity.ActivityEntityNames;
import com.fitness.aiservice.constants.message.common.CommonMessages;
import com.fitness.aiservice.constants.message.user.UserEntityNames;

public class RecommendationMessages {
    private static final String BASE_NAME = RecommendationEntityNames.RECOMMENDATION;
    private static final String BASE_NAME_PLURAL = RecommendationEntityNames.RECOMMENDATIONS;

    private RecommendationMessages() {
    }

    //  <========================== Success Messages ===============================>
    public static final String RECOMMENDATION_CREATED = BASE_NAME + " " + CommonMessages.CREATED_SUCCESSFULLY;

    public static final String USER_RECOMMENDATIONS_FETCHED = UserEntityNames.USER + " " + RecommendationEntityNames.RECOMMENDATIONS_LC + " " + CommonMessages.FETCHED_SUCCESSFULLY;

    public static final String ACTIVITY_RECOMMENDATION_FETCHED = ActivityEntityNames.ACTIVITY + " " + RecommendationEntityNames.RECOMMENDATION_LC + " " + CommonMessages.FETCHED_SUCCESSFULLY;

    //  <========================== Error Messages ===============================>
    public static final String ACTIVITY_RECOMMENDATION_NOT_FOUND = ActivityEntityNames.ACTIVITY + " " + RecommendationEntityNames.RECOMMENDATION_LC + " " + CommonMessages.NOT_FOUND;

}
