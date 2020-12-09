package ru.twitting.petproject.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.model.dto.ExtraInfoDto;
import ru.twitting.petproject.model.dto.GeoDto;
import ru.twitting.petproject.model.dto.PetDto;
import ru.twitting.petproject.model.dto.UserResponseDto;

@Data
@NoArgsConstructor
public class ReportResponse {

    private Long id;
    private UserResponseDto user;
    private PetDto pet;
    private GeoDto geo;
    private ExtraInfoDto extraInfo;
    private Double distance;
    private Boolean owner;
}
