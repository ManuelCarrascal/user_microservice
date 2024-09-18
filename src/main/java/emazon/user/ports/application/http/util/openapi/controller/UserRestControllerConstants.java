package emazon.user.ports.application.http.util.openapi.controller;

public class UserRestControllerConstants {
    public static final String TAG_NAME = "User Management";
    public static final String TAG_DESCRIPTION = "Endpoints for managing users";
    public static final String OPERATION_SUMMARY_SAVE_WAREHOUSE_ASST = "Save a new warehouse assistant user";
    public static final String OPERATION_SUMMARY_REGISTER_CLIENT = "Register a new client user";
    public static final String OPERATION_DESCRIPTION_REGISTER_CLIENT = "This endpoint registers a new client user in the system.";
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";

    private UserRestControllerConstants() {
    }
}
