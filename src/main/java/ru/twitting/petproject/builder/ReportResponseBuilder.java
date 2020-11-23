package ru.twitting.petproject.builder;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.entity.TagEntity;
import ru.twitting.petproject.model.dto.ExtraInfoDto;
import ru.twitting.petproject.model.dto.GeoDto;
import ru.twitting.petproject.model.dto.PetDto;
import ru.twitting.petproject.model.dto.UserReportResponseDto;
import ru.twitting.petproject.model.dto.response.ReportResponse;

import java.util.Optional;
import java.util.stream.Collectors;

import static ru.twitting.petproject.util.PointUtils.distance;


@RequiredArgsConstructor
public class ReportResponseBuilder {

    private final ReportEntity entity;
    private final Point point;

    public ReportResponse build() {
        var response = new ReportResponse();
        response.setExtraInfo(getExtraInfo());
        response.setGeo(getGeo());
        response.setPet(getPet());
        response.setUser(getUser());
        Optional.ofNullable(point).ifPresent(it -> response.setDistance(distance(point, entity.getGeoLocation())));
        return response;
    }

    private UserReportResponseDto getUser() {
        var user = new UserReportResponseDto();
        var userEntity = entity.getUser();
        user.setName(userEntity.getName());
        user.setEmail(Boolean.TRUE.equals(userEntity.getEmailShown()) ? userEntity.getEmail() : null);
        user.setPhone(Boolean.TRUE.equals(userEntity.getPhoneShown()) ? userEntity.getPhone() : null);
        return user;
    }

    private PetDto getPet() {
        var pet = new PetDto();
        pet.setName(entity.getPetName());
        pet.setType(entity.getPetType());
        pet.setTags(entity.getTags().stream()
                .map(TagEntity::getName)
                .collect(Collectors.toSet()));
        pet.setBreed(entity.getBreed());
        pet.setPhotos(entity.getPhotos());
        pet.setSex(entity.getSex());
        return pet;
    }

    private GeoDto getGeo() {
        var geo = new GeoDto();
        geo.setDescription(entity.getGeoDescription());
        geo.setRadius(entity.getRadius());
        geo.setLongitude(entity.getGeoLocation().getX());
        geo.setLatitude(entity.getGeoLocation().getY());
        return geo;
    }

    private ExtraInfoDto getExtraInfo() {
        var info = new ExtraInfoDto();
        info.setComment(entity.getComment());
        info.setLostFoundDate(entity.getLostFoundDate());
        return info;
    }
}
