package emazon.user.domain.exception;

public class AgeValidationException extends RuntimeException {
    public AgeValidationException(String message) {
        super(message);
    }
}
