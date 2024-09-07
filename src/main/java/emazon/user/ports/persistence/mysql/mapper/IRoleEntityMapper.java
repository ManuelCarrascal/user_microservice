package emazon.user.ports.persistence.mysql.mapper;

import emazon.user.domain.model.Role;
import emazon.user.ports.persistence.mysql.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {
    Role toRole(RoleEntity roleEntity);
}
