package emazon.user.domain.api;

import emazon.user.domain.model.User;

public interface IUserServicePort {
    void saveWarehouseAsstUser(User user);

    void saveClientUser(User user);
}
