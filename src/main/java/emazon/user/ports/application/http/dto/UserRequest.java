package emazon.user.ports.application.http.dto;

import emazon.user.ports.application.http.util.UserValidationConstants;
import emazon.user.ports.application.http.util.openapi.controller.user.UserRequestConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = UserRequestConstants.USER_REQUEST_DESCRIPTION)
public class UserRequest implements Serializable {
    @NotBlank(message = UserValidationConstants.NAME_MANDATORY)
    @Size(min = UserValidationConstants.NAME_MIN_SIZE, max = UserValidationConstants.NAME_MAX_SIZE, message = UserValidationConstants.NAME_SIZE)
    @Schema(description = UserRequestConstants.USER_NAME_DESCRIPTION, example = UserRequestConstants.USER_NAME_EXAMPLE)
    private String userName;

    @NotBlank(message = UserValidationConstants.LAST_NAME_MANDATORY)
    @Size(min = UserValidationConstants.LAST_NAME_MIN_SIZE, max = UserValidationConstants.LAST_NAME_MAX_SIZE, message = UserValidationConstants.LAST_NAME_SIZE)
    @Schema(description = UserRequestConstants.USER_LAST_NAME_DESCRIPTION, example = UserRequestConstants.USER_LAST_NAME_EXAMPLE)
    private String userLastName;

    @NotBlank(message = UserValidationConstants.IDENTITY_DOCUMENT_MANDATORY)
    @Pattern(regexp = UserValidationConstants.IDENTITY_DOCUMENT_REGEX, message = UserValidationConstants.IDENTITY_DOCUMENT_PATTERN)
    @Schema(description = UserRequestConstants.USER_IDENTITY_DOCUMENT_DESCRIPTION, example = UserRequestConstants.USER_IDENTITY_DOCUMENT_EXAMPLE)
    private String userIdentityDocument;

    @NotBlank(message = UserValidationConstants.PHONE_MANDATORY)
    @Pattern(regexp = UserValidationConstants.PHONE_REGEX, message = UserValidationConstants.PHONE_PATTERN)
    @Schema(description = UserRequestConstants.USER_PHONE_DESCRIPTION, example = UserRequestConstants.USER_PHONE_EXAMPLE)
    private String userPhone;

    @NotBlank(message = UserValidationConstants.EMAIL_MANDATORY)
    @Email(message = UserValidationConstants.EMAIL_PATTERN)
    @Schema(description = UserRequestConstants.USER_EMAIL_DESCRIPTION, example = UserRequestConstants.USER_EMAIL_EXAMPLE)
    private String userEmail;

    @NotBlank(message = UserValidationConstants.PASSWORD_MANDATORY)
    @Size(min = UserValidationConstants.PASSWORD_MIN_SIZE, max = UserValidationConstants.PASSWORD_MAX_SIZE, message = UserValidationConstants.PASSWORD_SIZE)
    @Pattern(regexp = UserValidationConstants.PASSWORD_REGEX, message = UserValidationConstants.PASSWORD_PATTERN)
    @Schema(description = UserRequestConstants.USER_PASSWORD_DESCRIPTION, example = UserRequestConstants.USER_PASSWORD_EXAMPLE)
    private String userPassword;

    @NotBlank(message = UserValidationConstants.BIRTHDATE_MANDATORY)
    @Pattern(regexp = UserValidationConstants.BIRTHDATE_REGEX, message = UserValidationConstants.BIRTHDATE_PATTERN)
    @Schema(description = UserRequestConstants.USER_BIRTHDATE_DESCRIPTION, example = UserRequestConstants.USER_BIRTHDATE_EXAMPLE)
    private String userBirthdate;
}