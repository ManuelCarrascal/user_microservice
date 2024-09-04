package emazon.user.domain.api.usecase;

import emazon.user.domain.api.IEncryptionService;
import emazon.user.domain.exception.EntityAlreadyExistsException;
import emazon.user.domain.model.User;
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
    IEncryptionService encryptionService;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveWarehouseAsstUser() {
        User user = new User();
        user.setUserEmail("test@example.com");
        user.setUserPassword("encodedPassword");
        user.setUserPhone("+1234567890");
        user.setUserIdentityDocument("123456789");
        user.setUserName("Test User");
        user.setUserLastName("Test Last Name");
        user.setUserBirthdate(LocalDate.of(1990, 1, 1));

        when(userPersistencePort.existsByEmail(user.getUserEmail())).thenReturn(false);
        doNothing().when(userValidation).validate(user);
        when(encryptionService.encodePassword(user.getUserPassword())).thenReturn("encodedPassword");
        doNothing().when(userPersistencePort).saveWarehouseAsstUser(user);

        userUseCase.saveWarehouseAsstUser(user);

        verify(userPersistencePort).existsByEmail(user.getUserEmail());
        verify(userValidation).validate(user);
        verify(encryptionService).encodePassword(user.getUserPassword());
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
        verify(userPersistencePort, never()).saveWarehouseAsstUser(user);
    }
}