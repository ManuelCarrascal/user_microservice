package emazon.user.domain.exception;

public class InvalidRoleException extends RuntimeException{
    public InvalidRoleException(String message) {
        super(message);
    }
}
