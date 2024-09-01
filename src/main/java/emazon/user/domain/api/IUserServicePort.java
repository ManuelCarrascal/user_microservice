package emazon.user.domain.api;

import emazon.user.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
}
