package emazon.user.ports.persistence.mysql.entity;

import emazon.user.ports.persistence.mysql.util.RoleEntityConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = RoleEntityConstants.TABLE_NAME)
public class RoleEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = RoleEntityConstants.COLUMN_ROLE_ID)
    private Long roleId;

    @Column(name = RoleEntityConstants.COLUMN_ROLE_NAME, unique = true, nullable = false, length = RoleEntityConstants.ROLE_NAME_MAX_LENGTH)
    private String roleName;

    @Column(name = RoleEntityConstants.COLUMN_ROLE_DESCRIPTION, length = RoleEntityConstants.ROLE_DESCRIPTION_MAX_LENGTH, nullable = false)
    private String roleDescription;

    @OneToMany(mappedBy = RoleEntityConstants.MAPPED_BY_ROLE, fetch = FetchType.LAZY)
    private List<UserEntity> users;


}