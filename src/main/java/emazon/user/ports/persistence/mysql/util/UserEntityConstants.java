package emazon.user.ports.persistence.mysql.util;

public class UserEntityConstants {
    public static final int USER_NAME_MAX_LENGTH = 50;
    public static final int USER_LASTNAME_MAX_LENGTH = 50;
    public static final int USER_PHONE_MAX_LENGTH = 13;
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_LASTNAME = "user_lastname";
    public static final String COLUMN_USER_IDENTITY_DOCUMENT = "user_identity_document";
    public static final String COLUMN_USER_PHONE = "user_phone";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_USER_BIRTHDATE = "user_birthdate";
    public static final String COLUMN_ROLE_ID = "role_id";

    private UserEntityConstants() {
    }
}
