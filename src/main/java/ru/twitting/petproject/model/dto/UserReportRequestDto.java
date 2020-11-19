package ru.twitting.petproject.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserReportRequestDto {

    @NotNull
    private String name;
    @NotNull
    private String password; //temporary
    private String phone;
    private Boolean phoneShown;
    private String email;
    private Boolean emailShown;

}
