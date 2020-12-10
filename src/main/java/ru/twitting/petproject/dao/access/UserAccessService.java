package ru.twitting.petproject.dao.access;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.dao.repository.UserRepository;
import ru.twitting.petproject.exception.BadRequestException;
import ru.twitting.petproject.exception.NotFoundException;
import ru.twitting.petproject.model.dto.UserDto;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j(topic = "PET-ACCESS-SERVICE")
@RequiredArgsConstructor
@Transactional
public class UserAccessService {

    private final UserRepository repository;

    public Optional<UserEntity> findByUsernameOptional(String username) {
        return repository.findByUsername(username);
    }

    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("Not found any user with name %s", username)));

    }

    public UserEntity save(UserEntity entity) {
        return repository.save(entity);
    }

    public void checkIfUserExist(UserDto userDto) {
        if (repository.existsByEmail(userDto.getEmail()) ||
                repository.existsByPhone(userDto.getPhone()) ||
                repository.existsByUsername(userDto.getUsername())) {
            throw new BadRequestException("User with provided email, phone or username already exists");
        }
    }
}
