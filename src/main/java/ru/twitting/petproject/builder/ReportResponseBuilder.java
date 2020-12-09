package ru.twitting.petproject.builder;

import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.entity.TagEntity;
import ru.twitting.petproject.model.dto.ExtraInfoDto;
import ru.twitting.petproject.model.dto.GeoDto;
import ru.twitting.petproject.model.dto.PetDto;
import ru.twitting.petproject.model.dto.response.ReportResponse;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.twitting.petproject.util.PointUtils.distance;

@RequiredArgsConstructor
public class ReportResponseBuilder {

    private final ReportEntity entity;
    private final Point point;

    public ReportResponseBuilder(ReportEntity entity) {
        this.entity = entity;
        this.point = null;
    }

    public ReportResponse build() {
        var response = new ReportResponse();
        response.setId(entity.getId());
        response.setExtraInfo(getExtraInfo());
        response.setGeo(getGeo());
        response.setPet(getPet());
        response.setUser(new UserResponseDtoBuilder(entity.getUser()).build());
        Optional.ofNullable(point).ifPresent(it -> response.setDistance(distance(point, entity.getGeoLocation())));
        response.setOwner(getOwner());
        return response;
    }

    private boolean getOwner() {
        var securityContext = SecurityContextHolder.getContext();
        return Objects.equals(securityContext.getAuthentication().getName(), entity.getUser().getUsername());
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
