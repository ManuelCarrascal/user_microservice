package emazon.user.domain.api;

public interface IEncryptionService {
    String encodePassword(String rawPassword);

    boolean matches(String userPassword, String userPassword1);
}
