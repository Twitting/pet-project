package ru.twitting.petproject.dao.access;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.dao.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j(topic = "PET-ACCESS-SERVICE")
@RequiredArgsConstructor
@Transactional
public class UserAccessService {

    private final UserRepository repository;

    public Optional<UserEntity> findByNameAndPassword(String name, String password) {
        return repository.findByNameAndPassword(name, password);
    }

    public UserEntity save(UserEntity entity) {
        return repository.save(entity);
    }

    public boolean existsByEmailOrPhone(String email, String phone) {
        return repository.existsByEmail(email) || repository.existsByPhone(phone);
    }

}
