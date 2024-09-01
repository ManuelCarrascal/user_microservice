package emazon.user.ports.application.http.mapper;

import emazon.user.domain.model.User;
import emazon.user.ports.application.http.dto.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mapping(source = "userBirthdate", target = "userBirthdate", qualifiedByName = "stringToLocalDate")
    User userRequestToUser(UserRequest userRequest);

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
