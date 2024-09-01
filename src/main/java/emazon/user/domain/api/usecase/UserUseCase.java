package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.domain.exception.EntityAlreadyExistsException;
import emazon.user.domain.util.EntityConstants;
import emazon.user.domain.util.UserValidation;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final UserValidation userValidation = new UserValidation();

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    public void saveUser(User user) {
        if(userPersistencePort.existsByEmail(user.getUserEmail())) {
            throw new EntityAlreadyExistsException(EntityConstants.USER_ENTITY_NAME);
        }
        userValidation.validate(user);

        userPersistencePort.saveUser(user);
    }

}
