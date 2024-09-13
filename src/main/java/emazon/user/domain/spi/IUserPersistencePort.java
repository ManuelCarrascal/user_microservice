package emazon.user.domain.spi;

import emazon.user.domain.model.User;
import emazon.user.ports.persistence.mysql.entity.UserEntity;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveWarehouseAsstUser(User user);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

    void saveClientUser(User user);
}
