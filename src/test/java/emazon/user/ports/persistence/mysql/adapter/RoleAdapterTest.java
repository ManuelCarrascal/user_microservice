package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.ports.persistence.mysql.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RoleAdapterTest {
    @Mock
    private IRoleRepository roleRepository;

    @InjectMocks
    private RoleAdapter roleAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRoleId() {
        String roleName = "ADMIN";
        Long expectedRoleId = 1L;
        when(roleRepository.getRoleIdByRoleName(roleName)).thenReturn(expectedRoleId);

        Long actualRoleId = roleAdapter.getRoleId(roleName);

        assertEquals(expectedRoleId, actualRoleId);
        verify(roleRepository).getRoleIdByRoleName(roleName);
    }
}