package ru.twitting.petproject.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ExtraInfoDto {

    @NotBlank
    private String comment;
    @NotNull
    private LocalDate lostFoundDate;
}
