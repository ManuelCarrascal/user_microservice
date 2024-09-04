package emazon.user.ports.persistence.mysql.util;

public class QueriesConstants {
    public static final String GET_ROLE_ID_BY_ROLE_NAME= "SELECT r.roleId FROM RoleEntity r WHERE r.roleName = :roleName";
    public static final String PARAM_ROLE_NAME = "roleName";
    private QueriesConstants(){
    }
}
