package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.domain.exception.EntityAlreadyExistsException;
import emazon.user.domain.util.EncryptionService;
import emazon.user.domain.util.EntityConstants;
import emazon.user.domain.util.UserValidation;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final UserValidation userValidation = new UserValidation();
    private final EncryptionService encryptionService;

    public UserUseCase(IUserPersistencePort userPersistencePort, EncryptionService encryptionService) {
        this.userPersistencePort = userPersistencePort;
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