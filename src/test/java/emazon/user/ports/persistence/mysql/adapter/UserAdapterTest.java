package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.User;
import emazon.user.ports.persistence.mysql.mapper.IUserEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class UserAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private UserAdapter userAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveWarehouseAsstUser() {
        User user = new User();
        UserEntity userEntity = new UserEntity();
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        userAdapter.saveWarehouseAsstUser(user);

        verify(userEntityMapper).toEntity(user);
        verify(userRepository).save(userEntity);
    }

    @Test
    void existsByEmail() {
        String email = "test@example.com";
        when(userRepository.findByUserEmail(email)).thenReturn(Optional.of(new UserEntity()));

        boolean exists = userAdapter.existsByEmail(email);

        assertTrue(exists);
        verify(userRepository).findByUserEmail(email);
    }

     @Test
     void findByEmail() {
         String email = "test@example.com";
         UserEntity userEntity = new UserEntity();
         when(userRepository.findByUserEmail(email)).thenReturn(Optional.of(userEntity));

         Optional<UserEntity> result = userAdapter.findByEmail(email);

         assertTrue(result.isPresent());
         assertEquals(userEntity, result.get());
         verify(userRepository).findByUserEmail(email);
     }

     @Test
     void findByEmail_NotFound() {
         String email = "notfound@example.com";
         when(userRepository.findByUserEmail(email)).thenReturn(Optional.empty());

         Optional<UserEntity> result = userAdapter.findByEmail(email);

         assertFalse(result.isPresent());
         verify(userRepository).findByUserEmail(email);
     }
}