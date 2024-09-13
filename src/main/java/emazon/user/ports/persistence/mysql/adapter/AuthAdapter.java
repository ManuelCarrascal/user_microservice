package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.Role;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IAuthPersistencePort;
import emazon.user.domain.spi.IRolePersistencePort;
import emazon.user.ports.persistence.mysql.util.AuthAdapterConstants;
import emazon.user.ports.persistence.mysql.util.JwtService;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IRolePersistencePort rolePersistencePort;

    @Override
    public User authenticate(String userEmail, String userPassword) {
        Authentication authUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userEmail,
                        userPassword
                )
        );

        UserEntity userEntity = (UserEntity) authUser.getPrincipal();
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setUserEmail(userEntity.getUserEmail());
        user.setRoleId(userEntity.getRole().getRoleId());


        return user;
    }

    @Override
    public String generateToken(User user) {

        return jwtService.generateToken(user, generateExtraClaims(user));
    }

    @Override
    public boolean validateCredentials(String userEmail, String userPassword) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEmail, userPassword)
            );
            return authentication.isAuthenticated();
        } catch (Exception e) {
            return false;
        }
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        Role role = rolePersistencePort.getRoleName(user.getRoleId());
        extraClaims.put(AuthAdapterConstants.AUTHORITIES_KEY, AuthAdapterConstants.ROLE_PREFIX + role.getRoleName());
        return extraClaims;
    }


}
