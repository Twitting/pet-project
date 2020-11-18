package ru.twitting.petproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.twitting.petproject.dao.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNameAndPassword(String name, String password);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
