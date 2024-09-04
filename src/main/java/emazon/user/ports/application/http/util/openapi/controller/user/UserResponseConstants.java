package emazon.user.ports.application.http.util.openapi.controller.user;

public class UserResponseConstants {
    public static final String USER_NAME_DESCRIPTION = "First name of the user";
    public static final String USER_NAME_EXAMPLE = "John";

    public static final String USER_LAST_NAME_DESCRIPTION = "Last name of the user";
    public static final String USER_LAST_NAME_EXAMPLE = "Doe";

    public static final String USER_IDENTITY_DOCUMENT_DESCRIPTION = "Identity document of the user";
    public static final String USER_IDENTITY_DOCUMENT_EXAMPLE = "123456789";

    public static final String USER_PHONE_DESCRIPTION = "Phone number of the user";
    public static final String USER_PHONE_EXAMPLE = "+1234567890";

    public static final String USER_EMAIL_DESCRIPTION = "Email address of the user";
    public static final String USER_EMAIL_EXAMPLE = "john.doe@example.com";

    public static final String USER_BIRTHDATE_DESCRIPTION = "Birthdate of the user";
    public static final String USER_BIRTHDATE_EXAMPLE = "01/01/1990";

    public static final String USER_RESPONSE_DESCRIPTION = "DTO for user response";

    private UserResponseConstants() {
    }
}
