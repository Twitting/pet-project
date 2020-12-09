package ru.twitting.petproject.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.twitting.petproject.model.dto.BaseResponse;
import ru.twitting.petproject.model.dto.UserDto;
import ru.twitting.petproject.model.dto.UserResponseDto;
import ru.twitting.petproject.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(value = "Report controller")
@Validated
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Создать пользователя")
    @PostMapping
    public ResponseEntity<BaseResponse<UserResponseDto>> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(new BaseResponse(userService.createUser(userDto)));
    }

    @ApiOperation(value = "Получить текущего пользователя")
    @GetMapping
    public ResponseEntity<BaseResponse<UserResponseDto>> getUser() {
        return ResponseEntity.ok(new BaseResponse(userService.getUser()));
    }

    @ApiOperation(value = "Изменить пользователя")
    @PatchMapping
    public ResponseEntity<BaseResponse<UserResponseDto>> changeUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(new BaseResponse(userService.changeUser(userDto)));
    }
}