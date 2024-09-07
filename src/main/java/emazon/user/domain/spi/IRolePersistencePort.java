package emazon.user.domain.spi;

import emazon.user.domain.model.Role;

public interface IRolePersistencePort {
    Long getRoleId(String roleName);
    Role getRoleName(Long roleId);
}


