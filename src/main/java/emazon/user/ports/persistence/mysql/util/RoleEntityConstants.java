package emazon.user.ports.persistence.mysql.util;

public class RoleEntityConstants {

    public static final int ROLE_NAME_MAX_LENGTH = 50;
    public static final int ROLE_DESCRIPTION_MAX_LENGTH = 200;
    public static final String TABLE_NAME = "role";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_ROLE_NAME = "role_name";
    public static final String COLUMN_ROLE_DESCRIPTION = "role_description";
    public static final String MAPPED_BY_ROLE = "role";

    private RoleEntityConstants() {
    }
}
