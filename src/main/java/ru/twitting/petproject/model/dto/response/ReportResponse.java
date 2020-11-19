package ru.twitting.petproject.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.model.dto.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReportResponse {

    @NotNull
    private UserReportResponseDto user;
    @NotNull
    private PetDto pet;
    @NotNull
    private GeoDto geo;
    @NotNull
    private ExtraInfoDto extraInfo;

}
