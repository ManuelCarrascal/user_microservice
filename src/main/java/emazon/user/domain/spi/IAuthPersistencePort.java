package emazon.user.domain.spi;

import emazon.user.domain.model.User;

public interface IAuthPersistencePort {
    User authenticate(String email, String password);
    String generateToken(User user);
    boolean validateCredentials(String userEmail, String userPassword);

}
