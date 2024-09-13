package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IEncryptionService;
import emazon.user.domain.exception.EntityAlreadyExistsException;
import emazon.user.domain.model.User;
import emazon.user.domain.spi.IRolePersistencePort;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.domain.util.UserValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private UserValidation userValidation;

    @Mock
    private IEncryptionService encryptionService;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort, userValidation, encryptionService, rolePersistencePort);
    }

    @Test
    void saveWarehouseAsstUser() {
        User user = new User();
        user.setUserEmail("test@example.com");
        user.setUserPassword("Password@123");
        user.setUserPhone("+1234567890");
        user.setUserIdentityDocument("123456789");
        user.setUserName("Test User");
        user.setUserLastName("Test Last Name");
        user.setUserBirthdate(LocalDate.of(1990, 1, 1));

        when(userPersistencePort.existsByEmail(user.getUserEmail())).thenReturn(false);
        doNothing().when(userValidation).validate(user);
        when(encryptionService.encodePassword("Password@123")).thenReturn("encodedPassword");
        when(rolePersistencePort.getRoleId("AUX_BODEGA")).thenReturn(2L);
        doNothing().when(userPersistencePort).saveWarehouseAsstUser(user);

        userUseCase.saveWarehouseAsstUser(user);

        verify(userPersistencePort).existsByEmail(user.getUserEmail());
        verify(userValidation).validate(user);
        verify(encryptionService).encodePassword("Password@123");
        verify(rolePersistencePort).getRoleId("AUX_BODEGA");
        verify(userPersistencePort).saveWarehouseAsstUser(user);
        assertEquals("encodedPassword", user.getUserPassword());
    }

    @Test
    void saveWarehouseAsstUser_throwsEntityAlreadyExistsException() {
        User user = new User();
        user.setUserEmail("test@example.com");

        when(userPersistencePort.existsByEmail(user.getUserEmail())).thenReturn(true);

        EntityAlreadyExistsException exception = assertThrows(EntityAlreadyExistsException.class, () -> userUseCase.saveWarehouseAsstUser(user));

        assertEquals("User already exists", exception.getMessage());
        verify(userPersistencePort).existsByEmail(user.getUserEmail());
        verify(userValidation, never()).validate(user);
        verify(encryptionService, never()).encodePassword(anyString());
        verify(rolePersistencePort, never()).getRoleId(anyString());
        verify(userPersistencePort, never()).saveWarehouseAsstUser(user);
    }

    @Test
    void saveClientUser() {
        User user = new User();
        user.setUserEmail("client@example.com");
        user.setUserPassword("Password@123");
        user.setUserPhone("+1234567890");
        user.setUserIdentityDocument("123456789");
        user.setUserName("Client User");
        user.setUserLastName("Client Last Name");
        user.setUserBirthdate(LocalDate.of(1990, 1, 1));

        when(userPersistencePort.existsByEmail(user.getUserEmail())).thenReturn(false);
        doNothing().when(userValidation).validate(user);
        when(encryptionService.encodePassword("Password@123")).thenReturn("encodedPassword");
        when(rolePersistencePort.getRoleId("CLIENTE")).thenReturn(1L);
        doNothing().when(userPersistencePort).saveClientUser(user);

        userUseCase.saveClientUser(user);

        verify(userPersistencePort).existsByEmail(user.getUserEmail());
        verify(userValidation).validate(user);
        verify(encryptionService).encodePassword("Password@123");
        verify(rolePersistencePort).getRoleId("CLIENTE");
        verify(userPersistencePort).saveClientUser(user);
        assertEquals("encodedPassword", user.getUserPassword());
    }

    @Test
    void saveClientUser_throwsEntityAlreadyExistsException() {
        User user = new User();
        user.setUserEmail("client@example.com");

        when(userPersistencePort.existsByEmail(user.getUserEmail())).thenReturn(true);

        EntityAlreadyExistsException exception = assertThrows(EntityAlreadyExistsException.class, () -> userUseCase.saveClientUser(user));

        assertEquals("User already exists", exception.getMessage());
        verify(userPersistencePort).existsByEmail(user.getUserEmail());
        verify(userValidation, never()).validate(user);
        verify(encryptionService, never()).encodePassword(anyString());
        verify(rolePersistencePort, never()).getRoleId(anyString());
        verify(userPersistencePort, never()).saveClientUser(user);
    }
}