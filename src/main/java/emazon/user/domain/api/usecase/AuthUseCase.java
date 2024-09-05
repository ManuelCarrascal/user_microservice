package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IAuthServicePort;
import emazon.user.domain.api.IEncryptionService;
import emazon.user.domain.exception.AuthenticationException;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.infrastructure.configuration.security.jwtconfiguration.JwtService;

public class AuthUseCase implements IAuthServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IEncryptionService encryptionService;
    private final JwtService jwtService;

    public AuthUseCase(IUserPersistencePort userPersistencePort, IEncryptionService encryptionService, JwtService jwtService) {
        this.userPersistencePort = userPersistencePort;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    @Override
    public String authenticate(String userEmail,String userPassword){

    }
}
