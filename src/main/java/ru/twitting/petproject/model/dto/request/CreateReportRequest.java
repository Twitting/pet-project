package ru.twitting.petproject.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.dto.ExtraInfoDto;
import ru.twitting.petproject.model.dto.GeoDto;
import ru.twitting.petproject.model.dto.PetDto;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateReportRequest {

    @NotNull
    private ReportType reportType;
    @NotNull
    private PetDto pet;
    @NotNull
    private GeoDto geo;
    @NotNull
    private ExtraInfoDto extraInfo;

}
