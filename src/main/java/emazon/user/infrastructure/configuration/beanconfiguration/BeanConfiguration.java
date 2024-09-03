package emazon.user.infrastructure.configuration.beanconfiguration;

import emazon.user.domain.api.IRoleServicePort;
import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.api.usecase.RoleUseCase;
import emazon.user.domain.api.usecase.UserUseCase;
import emazon.user.domain.spi.IRolePersistencePort;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.domain.util.EncryptionService;
import emazon.user.infrastructure.configuration.BCryptEncryptionService;
import emazon.user.ports.persistence.mysql.adapter.RoleAdapter;
import emazon.user.ports.persistence.mysql.adapter.UserAdapter;
import emazon.user.ports.persistence.mysql.mapper.IUserEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IRoleRepository;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public EncryptionService encryptionService() {
        return new BCryptEncryptionService();
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), encryptionService());
    }

    @Bean
    public IRolePersistencePort  rolePersistencePort(){
        return new RoleAdapter(roleRepository);
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }


}
