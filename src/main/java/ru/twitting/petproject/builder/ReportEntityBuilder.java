package ru.twitting.petproject.builder;

import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Component;
import ru.twitting.petproject.dao.access.TagAccessService;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.mapper.impl.UserReportDtoMapper;
import ru.twitting.petproject.model.dto.UserReportDto;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.util.PointUtils;

@Component
@RequiredArgsConstructor
public class ReportEntityBuilder {

    private final TagAccessService tagAccessService;
    private final UserAccessService userAccessService;
    private final UserReportDtoMapper userReportDtoMapper;

    public ReportEntity build(CreateReportRequest request) {
        Assert.notNull(request, "CreateReportRequest");
        var report = new ReportEntity();
        report.setUser(getUser(request.getUser()));
        var pet = request.getPet();
        report.setTags(tagAccessService.findOrCreateByNames(pet.getTags()));
        report.setBreed(pet.getBreed());
        report.setPetName(pet.getName());
        report.setPhotos(pet.getPhotos());
        report.setPetType(pet.getType());
        var geo = request.getGeo();
        report.setGeoLocation(PointUtils.ofPostGis(geo.getLatitude(), geo.getLongitude()));
        report.setRadius(geo.getRadius());
        report.setGeoDescription(geo.getDescription());
        var extraInfo = request.getExtraInfo();
        report.setComment(extraInfo.getComment());
        report.setMissingDate(extraInfo.getMissingDate());
        return report;
    }

    private UserEntity getUser(UserReportDto userDto) {
        return userAccessService.findByNameAndPassword(userDto.getName(), userDto.getPassword())
                .orElseGet(() -> userReportDtoMapper.convertToDestination(userDto));
    }
}
