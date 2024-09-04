package emazon.user.domain.exception;

public class EntityAlreadyExistsException extends  RuntimeException {
    public EntityAlreadyExistsException(String entityName) {
        super(entityName + " already exists");
    }
}
