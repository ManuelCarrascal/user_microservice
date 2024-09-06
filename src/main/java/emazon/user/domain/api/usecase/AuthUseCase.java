package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IAuthServicePort;
import emazon.user.domain.exception.AuthenticationException;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IAuthPersistencePort;


public class AuthUseCase implements IAuthServicePort {

    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login (String email, String password) {
        if(!authPersistencePort.validateCredentials(email, password)) {
            throw new AuthenticationException("invalid credentials");
        }

        User user = authPersistencePort.authenticate(email, password);
        return authPersistencePort.generateToken(user);
    }

}
