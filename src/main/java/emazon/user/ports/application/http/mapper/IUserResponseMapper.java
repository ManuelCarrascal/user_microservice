package emazon.user.ports.application.http.mapper;

import emazon.user.domain.model.User;
import emazon.user.ports.application.http.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    UserResponse userToUserResponse(User user);

}