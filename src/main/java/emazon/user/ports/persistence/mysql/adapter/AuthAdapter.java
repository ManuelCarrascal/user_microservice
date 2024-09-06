package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.User;
import emazon.user.domain.spi.IAuthPersistencePort;
import emazon.user.ports.persistence.mysql.util.JwtService;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import emazon.user.ports.persistence.mysql.mapper.IUserEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User authenticate(String userEmail, String userPassword) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("User not found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userEmail,
                        userPassword
                )
        );
       return  userEntityMapper.toUser(userEntity);
    }

    @Override
    public String generateToken(User user) {
        UserEntity userEntity = userRepository.findByUserId(user.getUserId()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return jwtService.generateToken(userEntity, generateExtraClaims(userEntity));
    }



    @Override
    public boolean validateCredentials(String userEmail, String userPassword) {
        UserEntity user = userRepository.findByUserEmail(userEmail).orElse(null);
        if(user == null){
            return false;
        }
        return passwordEncoder.matches(userPassword, user.getUserPassword());
    }


    private Map<String, Object> generateExtraClaims(UserEntity userEntity) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("authorities",userEntity.getAuthorities());
        return extraClaims;
    }
}
