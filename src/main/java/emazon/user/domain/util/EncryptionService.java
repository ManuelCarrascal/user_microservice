package emazon.user.domain.util;

public interface EncryptionService {
    String encodePassword(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
