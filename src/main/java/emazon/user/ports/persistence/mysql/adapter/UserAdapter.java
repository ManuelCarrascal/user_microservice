package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.User;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.ports.persistence.mysql.entity.UserEntity;
import emazon.user.ports.persistence.mysql.mapper.IUserEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {
    private  final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveWarehouseAsstUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByUserEmail(email).isPresent();
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public void saveClientUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }
}
