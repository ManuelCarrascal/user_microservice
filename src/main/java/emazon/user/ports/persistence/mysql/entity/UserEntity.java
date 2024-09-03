package emazon.user.ports.persistence.mysql.entity;

import emazon.user.ports.persistence.mysql.util.UserEntityConstants;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = UserEntityConstants.TABLE_NAME)
public class UserEntity implements UserDetails {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = UserEntityConstants.COLUMN_ROLE_ID)
    private RoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
    }

    @Override
    public String getPassword() {
        return this.getUserPassword();
    }

    @Override
    public String getUsername() {
        return this.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}