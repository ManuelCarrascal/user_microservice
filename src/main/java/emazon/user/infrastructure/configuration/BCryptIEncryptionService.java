package emazon.user.infrastructure.configuration;

import emazon.user.domain.api.IEncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptIEncryptionService implements IEncryptionService {
    private final PasswordEncoder passwordEncoder;

    public BCryptIEncryptionService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }


}
