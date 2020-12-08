package ru.twitting.petproject.builder;

import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.model.base.UserAuthorityType;
import ru.twitting.petproject.model.dto.UserDto;
import ru.twitting.petproject.security.model.SecurityUser;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserEntityBuilder {

    private final PasswordEncoder passwordEncoder;
    private final UserAccessService userAccessService;

    public UserEntity build(UserDto userDto) {
        Assert.notNull(userDto);
        Assert.isTrue(Objects.equals(userDto.getPassword(), userDto.getMatchingPassword()));
        userAccessService.checkIfUserExist(userDto);
        var userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setShownName(userDto.getShownName());
        userEntity.setAuthorityType(UserAuthorityType.USER);
        userEntity.setPhone(userDto.getPhone());
        userEntity.setPhoneShown(userDto.getPhoneShown());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setEmailShown(userDto.getEmailShown());
        return userEntity;
    }
}
