package ru.twitting.petproject.builder;

import lombok.RequiredArgsConstructor;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.model.dto.UserResponseDto;

@RequiredArgsConstructor
public class UserResponseDtoBuilder {

    private final UserEntity userEntity;

    public UserResponseDto build() {
        var user = new UserResponseDto();
        user.setName(userEntity.getShownName());
        user.setEmail(Boolean.TRUE.equals(userEntity.getEmailShown()) ? userEntity.getEmail() : null);
        user.setPhone(Boolean.TRUE.equals(userEntity.getPhoneShown()) ? userEntity.getPhone() : null);
        return user;
    }
}
