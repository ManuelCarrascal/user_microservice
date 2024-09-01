package emazon.user.domain.spi;

import emazon.user.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
    boolean existsByEmail(String email);

}
