package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.Role;
import emazon.user.ports.persistence.mysql.entity.RoleEntity;
import emazon.user.ports.persistence.mysql.mapper.IRoleEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleAdapterTest {
    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

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

    @Test
    void getRoleName() {
        Long roleId = 1L;
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(roleId);
        roleEntity.setRoleName("ADMIN");

        Role expectedRole = new Role();
        expectedRole.setRoleId(roleId);
        expectedRole.setRoleName("ADMIN");

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(roleEntity));
        when(roleEntityMapper.toRole(roleEntity)).thenReturn(expectedRole);

        Role actualRole = roleAdapter.getRoleName(roleId);

        assertEquals(expectedRole, actualRole);
        verify(roleRepository).findById(roleId);
        verify(roleEntityMapper).toRole(roleEntity);
    }

    @Test
    void getRoleName_NotFound() {
        Long roleId = 999L;

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> roleAdapter.getRoleName(roleId));

        verify(roleRepository).findById(roleId);
        verify(roleEntityMapper, never()).toRole(any());
    }
}