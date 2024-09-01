package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IRoleServicePort;
import emazon.user.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Long getRoleId(String roleName) {
       return  rolePersistencePort.getRoleId(roleName);
    }
}
