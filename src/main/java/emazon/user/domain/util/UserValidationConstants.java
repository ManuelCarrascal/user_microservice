package emazon.user.domain.util;

public class UserValidationConstants {
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    public static final String PHONE_REGEX = "^\\+?\\d{1,13}$";
    public static final String IDENTITY_DOCUMENT_REGEX = "^\\d+$";

    public static final String MISSING_FIELD = "All fields are required";
    public static final String EMAIL_MANDATORY = "Email is mandatory";
    public static final String EMAIL_INVALID = "Email should be valid";
    public static final String PHONE_MANDATORY = "Phone is mandatory";
    public static final String PHONE_INVALID = "Phone must be a maximum of 13 digits and can contain the symbol +";
    public static final String IDENTITY_DOCUMENT_MANDATORY = "Identity Document is mandatory";
    public static final String IDENTITY_DOCUMENT_INVALID = "Identity Document should be valid";
    public static final String AGE_INVALID = "User must be an adult";
    public static final int MINIMUM_AGE = 18;



    private UserValidationConstants() {
    }
}
