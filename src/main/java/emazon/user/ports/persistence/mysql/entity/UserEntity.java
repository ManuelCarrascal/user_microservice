package emazon.user.ports.persistence.mysql.entity;

import emazon.user.ports.persistence.mysql.util.UserEntityConstants;
import emazon.user.ports.persistence.mysql.util.openapi.UserEntityOpenApiConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = UserEntityConstants.TABLE_NAME)
@Schema(description = UserEntityOpenApiConstants.USER_ENTITY_DESCRIPTION)
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserEntityConstants.COLUMN_USER_ID)
    @Schema(description = UserEntityOpenApiConstants.USER_ID_DESCRIPTION, example = UserEntityOpenApiConstants.USER_ID_EXAMPLE)
    private Long userId;

    @Column(name = UserEntityConstants.COLUMN_USER_NAME, nullable = false, length = UserEntityConstants.USER_NAME_MAX_LENGTH)
    @Schema(description = UserEntityOpenApiConstants.USER_NAME_DESCRIPTION, example = UserEntityOpenApiConstants.USER_NAME_EXAMPLE)
    private String userName;

    @Column(name = UserEntityConstants.COLUMN_USER_LASTNAME, nullable = false, length = UserEntityConstants.USER_LASTNAME_MAX_LENGTH)
    @Schema(description = UserEntityOpenApiConstants.USER_LASTNAME_DESCRIPTION, example = UserEntityOpenApiConstants.USER_LASTNAME_EXAMPLE)
    private String userLastName;

    @Column(name = UserEntityConstants.COLUMN_USER_IDENTITY_DOCUMENT, nullable = false)
    @Schema(description = UserEntityOpenApiConstants.USER_IDENTITY_DOCUMENT_DESCRIPTION, example = UserEntityOpenApiConstants.USER_IDENTITY_DOCUMENT_EXAMPLE)
    private String userIdentityDocument;

    @Column(name = UserEntityConstants.COLUMN_USER_PHONE, nullable = false, length = UserEntityConstants.USER_PHONE_MAX_LENGTH)
    @Schema(description = UserEntityOpenApiConstants.USER_PHONE_DESCRIPTION, example = UserEntityOpenApiConstants.USER_PHONE_EXAMPLE)
    private String userPhone;

    @Column(name = UserEntityConstants.COLUMN_USER_EMAIL, unique = true, nullable = false)
    @Schema(description = UserEntityOpenApiConstants.USER_EMAIL_DESCRIPTION, example = UserEntityOpenApiConstants.USER_EMAIL_EXAMPLE)
    private String userEmail;

    @Column(name = UserEntityConstants.COLUMN_USER_PASSWORD, nullable = false)
    @Schema(description = UserEntityOpenApiConstants.USER_PASSWORD_DESCRIPTION, example = UserEntityOpenApiConstants.USER_PASSWORD_EXAMPLE)
    private String userPassword;

    @Column(name = UserEntityConstants.COLUMN_USER_BIRTHDATE, nullable = false)
    @Schema(description = UserEntityOpenApiConstants.USER_BIRTHDATE_DESCRIPTION, example = UserEntityOpenApiConstants.USER_BIRTHDATE_EXAMPLE)
    private LocalDate userBirthdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = UserEntityConstants.COLUMN_ROLE_ID)
    @Schema(description = UserEntityOpenApiConstants.USER_ROLE_DESCRIPTION)
    private RoleEntity role;
}