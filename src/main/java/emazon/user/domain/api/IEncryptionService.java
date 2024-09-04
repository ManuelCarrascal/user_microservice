package emazon.user.domain.api;

public interface IEncryptionService {
    String encodePassword(String rawPassword);

}
