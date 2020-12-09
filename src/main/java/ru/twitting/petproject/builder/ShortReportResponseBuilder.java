package ru.twitting.petproject.builder;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.model.dto.response.ShortReportResponse;

import java.util.Optional;

import static ru.twitting.petproject.util.PointUtils.distance;

@RequiredArgsConstructor
public class ShortReportResponseBuilder {

    private final ReportEntity entity;
    private final Point point;

    public ShortReportResponseBuilder(ReportEntity entity) {
        this.entity = entity;
        this.point = null;
    }

    public ShortReportResponse build() {
        var response = new ShortReportResponse();
        response.setId(entity.getId());
        response.setBreed(entity.getBreed());
        response.setComment(entity.getComment());
        response.setLongitude(entity.getGeoLocation().getX());
        response.setLatitude(entity.getGeoLocation().getY());
        response.setType(entity.getPetType());
        response.setSex(entity.getSex());
        Optional.ofNullable(point).ifPresent(it -> response.setDistance(distance(point, entity.getGeoLocation())));
        return response;
    }
}
