package emazon.user.infrastructure.configuration.util;

public class AuthenticationFilterConstants {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String USER_NOT_FOUND = "User not found";
    public static final int TOKEN_PREFIX_LENGTH = 7;
    public static final String INVALID_TOKEN = "Invalid token";

    private AuthenticationFilterConstants() {
    }

}
