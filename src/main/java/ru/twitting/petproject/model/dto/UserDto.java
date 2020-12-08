package ru.twitting.petproject.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserDto {

    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String shownName;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String matchingPassword;
    private String newPassword;
    private String phone;
    private Boolean phoneShown;
    private String email;
    private Boolean emailShown;

}
