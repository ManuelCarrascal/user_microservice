package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.Role;
import emazon.user.domain.model.User;
import emazon.user.ports.persistence.mysql.entity.RoleEntity;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import emazon.user.ports.persistence.mysql.util.AuthAdapterConstants;
import emazon.user.ports.persistence.mysql.util.JwtService;
import emazon.user.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthAdapterTest {

     @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private AuthAdapter authAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticate() {
        String email = "test@example.com";
        String password = "password123";

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setUserEmail(email);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(1L);
        roleEntity.setRoleName("ROLE_USER");
        userEntity.setRole(roleEntity);

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userEntity);

        User user = authAdapter.authenticate(email, password);

        assertNotNull(user);
        assertEquals(1L, user.getUserId());
        assertEquals(email, user.getUserEmail());
        assertEquals(1L, user.getRoleId());
    }

    @Test
    void testGenerateToken() {
        User user = new User();
        user.setUserId(1L);
        user.setUserEmail("test@example.com");
        user.setRoleId(1L);

        String expectedToken = "jwt-token";
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(AuthAdapterConstants.AUTHORITIES_KEY, "ROLE_USER");

        when(jwtService.generateToken(any(User.class), anyMap()))
                .thenReturn(expectedToken);

        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("USER");
        when(rolePersistencePort.getRoleName(user.getRoleId())).thenReturn(role);

        String token = authAdapter.generateToken(user);

        assertEquals(expectedToken, token);
    }

    @Test
    void testValidateCredentials() {
        String email = "test@example.com";
        String password = "password123";
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);

        boolean result = authAdapter.validateCredentials(email, password);

        assertTrue(result);
    }



    @Test
    void testValidateCredentials_InvalidCredentials() {
        String email = "invalid@example.com";
        String password = "wrongpassword";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Invalid credentials"));

        boolean result = authAdapter.validateCredentials(email, password);

        assertFalse(result);
    }
}
