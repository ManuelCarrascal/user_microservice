package emazon.user.ports.persistence.mysql.mapper;

import emazon.user.domain.model.User;
import emazon.user.ports.persistence.mysql.entity.RoleEntity;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    @Mapping(source = "roleId", target = "role")
    UserEntity toEntity(User user);

    default RoleEntity map(Long roleId) {
        if (roleId == null) {
            return null;
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(roleId);
        return roleEntity;
    }

    User toUser(UserEntity userEntity);
}
