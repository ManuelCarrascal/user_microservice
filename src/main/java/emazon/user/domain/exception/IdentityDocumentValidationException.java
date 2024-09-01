package emazon.user.domain.exception;

public class IdentityDocumentValidationException extends RuntimeException {
    public IdentityDocumentValidationException(String message) {
        super(message);
    }
}
