package emazon.user.ports.persistence.mysql.repository;

import emazon.user.ports.persistence.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
   Optional<UserEntity> findByUserEmail(String userEmail);
   Optional<UserEntity> findByUserId(Long userId);
}
