package ru.twitting.petproject.service;

import ru.twitting.petproject.model.dto.UserDto;
import ru.twitting.petproject.model.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserDto user);

    UserResponseDto getUser();

    UserResponseDto changeUser(UserDto user);

}
