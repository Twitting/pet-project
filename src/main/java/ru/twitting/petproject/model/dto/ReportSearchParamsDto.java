package ru.twitting.petproject.model.dto;

import lombok.Builder;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.base.SexType;

import java.util.Set;

@Data
@Builder
public class ReportSearchParamsDto {

    private ReportType reportType;
    private SexType sexType;
    private PetType petType;
    private Point geoLocation;
    private Double radius;
    private Set<String> tags;

}
