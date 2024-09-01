package emazon.user.ports.application.http.dto;

import emazon.user.ports.application.http.util.UserValidationConstants;
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
public class UserRequest implements Serializable {
    @NotBlank(message = UserValidationConstants.NAME_MANDATORY)
    @Size(min = UserValidationConstants.NAME_MIN_SIZE, max = UserValidationConstants.NAME_MAX_SIZE, message = UserValidationConstants.NAME_SIZE)
    private String userName;

    @NotBlank(message = UserValidationConstants.LAST_NAME_MANDATORY)
    @Size(min = UserValidationConstants.LAST_NAME_MIN_SIZE, max = UserValidationConstants.LAST_NAME_MAX_SIZE, message = UserValidationConstants.LAST_NAME_SIZE)
    private String userLastName;

    @NotBlank(message = UserValidationConstants.IDENTITY_DOCUMENT_MANDATORY)
    @Pattern(regexp = UserValidationConstants.IDENTITY_DOCUMENT_REGEX, message = UserValidationConstants.IDENTITY_DOCUMENT_PATTERN)
    private String userIdentityDocument;

    @NotBlank(message = UserValidationConstants.PHONE_MANDATORY)
    @Pattern(regexp = UserValidationConstants.PHONE_REGEX, message = UserValidationConstants.PHONE_PATTERN)
    private String userPhone;

    @NotBlank(message = UserValidationConstants.EMAIL_MANDATORY)
    @Email(message = UserValidationConstants.EMAIL_PATTERN)
    private String userEmail;

    @NotBlank(message = UserValidationConstants.PASSWORD_MANDATORY)
    @Size(min = UserValidationConstants.PASSWORD_MIN_SIZE, max = UserValidationConstants.PASSWORD_MAX_SIZE, message = UserValidationConstants.PASSWORD_SIZE)
    @Pattern(regexp = UserValidationConstants.PASSWORD_REGEX, message = UserValidationConstants.PASSWORD_PATTERN)
    private String userPassword;

    @NotBlank(message = UserValidationConstants.BIRTHDATE_MANDATORY)
    @Pattern(regexp = UserValidationConstants.BIRTHDATE_REGEX, message = UserValidationConstants.BIRTHDATE_PATTERN)
    private String userBirthdate;
}