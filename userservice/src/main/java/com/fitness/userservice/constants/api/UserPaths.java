package com.fitness.userservice.constants.api;

public final class UserPaths {
    public final static String BASE = ApiPaths.API_V1 + "/users";

    public final static String BY_ID = "/{keycloakId}";
    public final static String USER_EXISTS = "/{keycloakId}/exists";
}
