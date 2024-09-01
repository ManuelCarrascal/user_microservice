package emazon.user.ports.persistence.mysql.repository;

import emazon.user.ports.persistence.mysql.entity.RoleEntity;
import emazon.user.ports.persistence.mysql.util.QueriesConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(QueriesConstants.GET_ROLE_ID_BY_ROLE_NAME)
    Long getRoleIdByRoleName(@Param(QueriesConstants.PARAM_ROLE_NAME) String roleName);

}