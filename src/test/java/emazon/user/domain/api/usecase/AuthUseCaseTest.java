package emazon.user.domain.api.usecase;

import emazon.user.domain.exception.AuthenticationException;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IAuthPersistencePort;
import emazon.user.domain.util.AuthValidationConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class AuthUseCaseTest {
    @Mock
    private IAuthPersistencePort authPersistencePort;

    @InjectMocks
    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginWithValidCredentials() {
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        String token = "valid-token";

        when(authPersistencePort.validateCredentials(email, password)).thenReturn(true);
        when(authPersistencePort.authenticate(email, password)).thenReturn(user);
        when(authPersistencePort.generateToken(user)).thenReturn(token);

        String result = authUseCase.login(email, password);

        assertEquals(token, result);
        verify(authPersistencePort).validateCredentials(email, password);
        verify(authPersistencePort).authenticate(email, password);
        verify(authPersistencePort).generateToken(user);
    }

    @Test
    void testLoginWithInvalidCredentials() {
        String email = "test@example.com";
        String password = "wrong-password";

        when(authPersistencePort.validateCredentials(email, password)).thenReturn(false);

        emazon.user.domain.exception.AuthenticationException thrown = assertThrows(AuthenticationException.class, () -> authUseCase.login(email, password));

        assertEquals(AuthValidationConstants.INVALID_CREDENTIALS, thrown.getMessage());
        verify(authPersistencePort).validateCredentials(email, password);
        verify(authPersistencePort, never()).authenticate(any(), any());
        verify(authPersistencePort, never()).generateToken(any());
    }
}