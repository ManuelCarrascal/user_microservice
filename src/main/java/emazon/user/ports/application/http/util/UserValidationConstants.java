package emazon.user.ports.application.http.util;

public class UserValidationConstants {
    public static final int NAME_MIN_SIZE = 2;
    public static final int NAME_MAX_SIZE = 50;
    public static final int LAST_NAME_MIN_SIZE = 2;
    public static final int LAST_NAME_MAX_SIZE = 50;
    public static final int PASSWORD_MIN_SIZE = 8;
    public static final int PASSWORD_MAX_SIZE = 20;

    private static final String AND = " and ";
    private static final String CHARACTERS = " characters";

    public static final String NAME_MANDATORY = "Name is mandatory";
    public static final String NAME_SIZE = "Name must be between " + NAME_MIN_SIZE + AND + NAME_MAX_SIZE + CHARACTERS;
    public static final String LAST_NAME_MANDATORY = "Last Name is mandatory";
    public static final String LAST_NAME_SIZE = "Last Name must be between " + LAST_NAME_MIN_SIZE + AND + LAST_NAME_MAX_SIZE + CHARACTERS;
    public static final String IDENTITY_DOCUMENT_MANDATORY = "Identity Document is mandatory";
    public static final String IDENTITY_DOCUMENT_PATTERN = "Identity Document should be valid";
    public static final String PHONE_MANDATORY = "Phone is mandatory";
    public static final String PHONE_PATTERN = "Phone must be a maximum of 13 digits and can contain the symbol +";
    public static final String EMAIL_MANDATORY = "Email is mandatory";
    public static final String EMAIL_PATTERN = "Email should be valid";
    public static final String PASSWORD_MANDATORY = "Password is mandatory";
    public static final String PASSWORD_SIZE = "Password must be between"+PASSWORD_MIN_SIZE+AND+PASSWORD_MAX_SIZE+CHARACTERS;
    public static final String PASSWORD_PATTERN = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character";
    public static final String BIRTHDATE_MANDATORY = "Birthdate is mandatory";
    public static final String BIRTHDATE_PATTERN = "Birthdate must be in the format dd/MM/yyyy";

    public static final String IDENTITY_DOCUMENT_REGEX = "^\\d$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,13}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String BIRTHDATE_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    private UserValidationConstants() {
    }
}
