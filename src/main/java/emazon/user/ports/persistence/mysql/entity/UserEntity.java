package emazon.user.ports.persistence.mysql.entity;

import emazon.user.ports.persistence.mysql.util.UserEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = UserEntityConstants.TABLE_NAME)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserEntityConstants.COLUMN_USER_ID)
    private Long userId;

    @Column(name = UserEntityConstants.COLUMN_USER_NAME, nullable = false, length = UserEntityConstants.USER_NAME_MAX_LENGTH)
    private String userName;

    @Column(name = UserEntityConstants.COLUMN_USER_LASTNAME, nullable = false, length = UserEntityConstants.USER_LASTNAME_MAX_LENGTH)
    private String userLastName;

    @Column(name = UserEntityConstants.COLUMN_USER_IDENTITY_DOCUMENT, nullable = false)
    private String userIdentityDocument;

    @Column(name = UserEntityConstants.COLUMN_USER_PHONE, nullable = false, length = UserEntityConstants.USER_PHONE_MAX_LENGTH)
    private String userPhone;

    @Column(name = UserEntityConstants.COLUMN_USER_EMAIL, unique = true, nullable = false)
    private String userEmail;

    @Column(name = UserEntityConstants.COLUMN_USER_PASSWORD, nullable = false)
    private String userPassword;

    @Column(name = UserEntityConstants.COLUMN_USER_BIRTHDATE, nullable = false)
    private LocalDate userBirthdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = UserEntityConstants.COLUMN_ROLE_ID)
    private RoleEntity role;
}
