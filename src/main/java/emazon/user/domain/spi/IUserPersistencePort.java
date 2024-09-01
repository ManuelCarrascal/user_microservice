package emazon.user.domain.spi;

import emazon.user.domain.model.User;

public interface IUserPersistencePort {
    void saveWarehouseAsstUser(User user);
    boolean existsByEmail(String email);

}
