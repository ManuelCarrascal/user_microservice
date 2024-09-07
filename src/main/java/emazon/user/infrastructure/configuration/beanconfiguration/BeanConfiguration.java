package emazon.user.infrastructure.configuration.beanconfiguration;

import emazon.user.domain.api.IAuthServicePort;
import emazon.user.domain.api.IRoleServicePort;
import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.api.usecase.AuthUseCase;
import emazon.user.domain.api.usecase.RoleUseCase;
import emazon.user.domain.api.usecase.UserUseCase;
import emazon.user.domain.spi.IAuthPersistencePort;
import emazon.user.domain.spi.IRolePersistencePort;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.domain.api.IEncryptionService;
import emazon.user.domain.util.UserValidation;
import emazon.user.infrastructure.configuration.BCryptIEncryptionHandler;
import emazon.user.ports.persistence.mysql.mapper.IRoleEntityMapper;
import emazon.user.ports.persistence.mysql.util.JwtService;
import emazon.user.ports.persistence.mysql.adapter.AuthAdapter;
import emazon.user.ports.persistence.mysql.adapter.RoleAdapter;
import emazon.user.ports.persistence.mysql.adapter.UserAdapter;
import emazon.user.ports.persistence.mysql.mapper.IUserEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IRoleRepository;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IEncryptionService encryptionService() {
        return new BCryptIEncryptionHandler();
    }

    @Bean
    public UserValidation userValidation() {
        return new UserValidation();
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(),userValidation(), encryptionService());
    }

    @Bean
    public IRolePersistencePort  rolePersistencePort(IRoleEntityMapper roleEntityMapper){
        return new RoleAdapter(roleRepository,roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort(IRoleEntityMapper roleEntityMapper){
        return new RoleUseCase(rolePersistencePort(roleEntityMapper));
    }

    @Bean
    public IAuthPersistencePort authPersistencePort(IRolePersistencePort rolePersistencePort){
        return new AuthAdapter(userRepository, userEntityMapper, authenticationManager, jwtService, passwordEncoder, rolePersistencePort);
    }
    @Bean
    public IAuthServicePort authServicePort(IAuthPersistencePort authPersistencePort){
        return new AuthUseCase(authPersistencePort);
    }



}
