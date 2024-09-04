package emazon.user.domain.api.usecase;

import emazon.user.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRoleId_WhenRoleExists_ShouldReturnRoleId(){
        String roleName="ADMIN";

        when(rolePersistencePort.getRoleId(roleName)).thenReturn(null);
        Long actualRoleId= roleUseCase.getRoleId(roleName);
        assertNull(actualRoleId);
    }

    @Test
    void getRoleId_whenRoleDoesNotExist_shouldReturnNull() {
        String roleName= "NO_EXIST_ROLE";
        when(rolePersistencePort.getRoleId(roleName)).thenReturn(null);

        Long actualRoleId= roleUseCase.getRoleId(roleName);
        assertNull(actualRoleId);
    }

}