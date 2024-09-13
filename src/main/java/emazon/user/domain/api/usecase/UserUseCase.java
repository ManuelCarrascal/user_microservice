package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.domain.exception.EntityAlreadyExistsException;
import emazon.user.domain.api.IEncryptionService;
import emazon.user.domain.util.EntityConstants;
import emazon.user.domain.util.UserValidation;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final UserValidation userValidation;
    private final IEncryptionService encryptionService;

    public UserUseCase(IUserPersistencePort userPersistencePort, UserValidation userValidation, IEncryptionService encryptionService) {
        this.userPersistencePort = userPersistencePort;
        this.userValidation = userValidation;
        this.encryptionService = encryptionService;
    }

    public void saveWarehouseAsstUser(User user) {
        if(userPersistencePort.existsByEmail(user.getUserEmail())) {
            throw new EntityAlreadyExistsException(EntityConstants.USER_ENTITY_NAME);
        }

        userValidation.validate(user);
        String encodedPassword = encryptionService.encodePassword(user.getUserPassword());
        user.setUserPassword(encodedPassword);
        userPersistencePort.saveWarehouseAsstUser(user);
    }
}