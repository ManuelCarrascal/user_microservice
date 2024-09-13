package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.Role;
import emazon.user.domain.spi.IRolePersistencePort;
import emazon.user.ports.persistence.mysql.entity.RoleEntity;
import emazon.user.ports.persistence.mysql.mapper.IRoleEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Long getRoleId(String roleName) {
        return roleRepository.getRoleIdByRoleName(roleName);
    }

    @Override
    public Role getRoleName(Long roleId) {
        RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow();
        return roleEntityMapper.toRole(roleEntity);
    }
}
