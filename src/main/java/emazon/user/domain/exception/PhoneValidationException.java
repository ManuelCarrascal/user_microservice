package emazon.user.domain.exception;

public class PhoneValidationException extends  RuntimeException{
    public PhoneValidationException(String message) {
        super(message);
    }
}
