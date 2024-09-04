package emazon.user.ports.application.http.dto;

import emazon.user.ports.application.http.util.openapi.controller.user.UserResponseConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = UserResponseConstants.USER_RESPONSE_DESCRIPTION)
public class UserResponse {
    @Schema(description = UserResponseConstants.USER_NAME_DESCRIPTION, example = UserResponseConstants.USER_NAME_EXAMPLE)
    private String userName;

    @Schema(description = UserResponseConstants.USER_LAST_NAME_DESCRIPTION, example = UserResponseConstants.USER_LAST_NAME_EXAMPLE)
    private String userLastName;

    @Schema(description = UserResponseConstants.USER_IDENTITY_DOCUMENT_DESCRIPTION, example = UserResponseConstants.USER_IDENTITY_DOCUMENT_EXAMPLE)
    private String userIdentityDocument;

    @Schema(description = UserResponseConstants.USER_PHONE_DESCRIPTION, example = UserResponseConstants.USER_PHONE_EXAMPLE)
    private String userPhone;

    @Schema(description = UserResponseConstants.USER_EMAIL_DESCRIPTION, example = UserResponseConstants.USER_EMAIL_EXAMPLE)
    private String userEmail;

    @Schema(description = UserResponseConstants.USER_BIRTHDATE_DESCRIPTION, example = UserResponseConstants.USER_BIRTHDATE_EXAMPLE)
    private LocalDate userBirthdate;
}
