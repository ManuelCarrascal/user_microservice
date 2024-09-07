package emazon.user.ports.persistence.mysql.mapper;

import emazon.user.domain.model.User;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

}
