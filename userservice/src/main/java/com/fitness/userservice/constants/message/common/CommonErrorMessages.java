package com.fitness.userservice.constants.message.common;

public final class CommonErrorMessages {


    private CommonErrorMessages() {
    }

    public static final String VALIDATION_FAILED =
            "Validation failed";

    public static final String INVALID_REQUEST_BODY =
            "Request body is missing or contains invalid data";

    public static final String ENDPOINT_NOT_FOUND =
            "Endpoint not found";

    public static final String METHOD_NOT_ALLOWED =
            "HTTP method not allowed for this endpoint";

    public static final String DATABASE_ERROR =
            "Database error occurred. Please try again later.";

    public static final String INTERNAL_SERVER_ERROR =
            "Something went wrong. Please try again later.";

    public static final String UNAUTHORIZED =
            "Authentication is required to access this resource";

    public static final String ACCESS_DENIED =
            "You do not have permission to access this resource";

    public static final String INVALID_CREDENTIALS =
            "Invalid credentials";

    public static final String AUTHENTICATION_FAILED =
            "Authentication failed";

    public static final String INVALID_AUTHENTICATION_PRINCIPAL =
            "Authentication principal is invalid";

    public static final String RESOURCE_ALREADY_EXISTS =
            "Resource already exists";

}
