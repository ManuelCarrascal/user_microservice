package emazon.user.ports.application.http.mapper;

import emazon.user.domain.model.User;
import emazon.user.ports.application.http.dto.UserRequest;
import emazon.user.ports.application.http.util.UserRequestMapperConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mapping(source = UserRequestMapperConstants.USER_BIRTHDATE, target = UserRequestMapperConstants.USER_BIRTHDATE, qualifiedByName = UserRequestMapperConstants.STRING_TO_LOCAL_DATE)
    @Mapping(target = UserRequestMapperConstants.USER_ID, ignore = true)
    @Mapping(target = UserRequestMapperConstants.ROLE_ID, ignore = true)
    User userRequestToUser(UserRequest userRequest);

    @Named(UserRequestMapperConstants.STRING_TO_LOCAL_DATE)
    default LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UserRequestMapperConstants.DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }
}