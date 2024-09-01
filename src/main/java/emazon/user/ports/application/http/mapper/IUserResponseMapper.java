package emazon.user.ports.application.http.mapper;

import emazon.user.ports.application.http.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    UserResponse userToUserResponse(emazon.user.domain.model.User user);
}
