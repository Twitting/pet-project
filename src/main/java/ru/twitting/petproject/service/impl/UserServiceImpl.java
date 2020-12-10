package ru.twitting.petproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.twitting.petproject.builder.UserEntityBuilder;
import ru.twitting.petproject.builder.UserResponseDtoBuilder;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.model.dto.UserDto;
import ru.twitting.petproject.model.dto.UserResponseDto;
import ru.twitting.petproject.service.UserService;

import java.util.Objects;
import java.util.Optional;

import static ru.twitting.petproject.util.UserVerificator.checkUsername;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAccessService userAccessService;
    private final UserEntityBuilder userEntityBuilder;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserDto user) {
        var savedUser = userAccessService.save(userEntityBuilder.build(user));
        return new UserResponseDtoBuilder(savedUser).build();
    }

    @Override
    public UserResponseDto getUser() {
        var context = SecurityContextHolder.getContext();
        var user = userAccessService.findByUsername(context.getAuthentication().getName());
        return new UserResponseDtoBuilder(user).build();
    }

    @Override
    @Transactional
    public UserResponseDto changeUser(UserDto user) {
        var userEntity = userAccessService.findByUsername(user.getUsername());
        checkUsername(user.getUsername());
        if (Objects.equals(user.getPassword(), user.getMatchingPassword()) && Objects.nonNull(user.getNewPassword())) {
            userEntity.setPassword(passwordEncoder.encode(user.getNewPassword()));
        }
        Optional.ofNullable(user.getShownName()).ifPresent(userEntity::setShownName);
        Optional.ofNullable(user.getEmailShown()).ifPresent(userEntity::setEmailShown);
        Optional.ofNullable(user.getEmail()).ifPresent(userEntity::setEmail);
        Optional.ofNullable(user.getPhoneShown()).ifPresent(userEntity::setPhoneShown);
        Optional.ofNullable(user.getPhone()).ifPresent(userEntity::setPhone);
        var savedUser = userAccessService.save(userEntity);
        return new UserResponseDtoBuilder(savedUser).build();
    }
}
