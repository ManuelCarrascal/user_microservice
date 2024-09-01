package emazon.user.ports.persistence.mysql.adapter;

import emazon.user.domain.model.User;
import emazon.user.domain.spi.IUserPersistencePort;
import emazon.user.ports.persistence.mysql.mapper.IUserEntityMapper;
import emazon.user.ports.persistence.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {
    private  final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByUserEmail(email).isPresent();
    }
}
