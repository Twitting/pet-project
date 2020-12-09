package ru.twitting.petproject.test.helper.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.base.SexType;
import ru.twitting.petproject.model.dto.*;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.model.dto.response.ReportResponse;
import ru.twitting.petproject.model.dto.response.ShortReportResponse;

import java.time.LocalDate;

import static ru.twitting.petproject.test.helper.generator.CommonGenerator.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DtoGenerator {

    public static ReportSearchParamsDto generateReportSearchParamsDto() {
        return ReportSearchParamsDto.builder()
                .geoLocation(generatePoint())
                .petType(generateOneOf(PetType.DOG, PetType.CAT))
                .sexType(generateOneOf(SexType.BOY, SexType.GIRL))
                .radius(generateDouble())
                .reportType(generateOneOf(ReportType.FOUND, ReportType.LOST))
                .tags(generateSet(3, CommonGenerator::generateString))
                .build();
    }

    public static ShortReportResponse generateShortReportResponse() {
        var dto = new ShortReportResponse();
        dto.setId(generateLong());
        dto.setSex(SexType.BOY);
        dto.setType(PetType.CAT);
        dto.setLatitude(generateDouble());
        dto.setLongitude(generateDouble());
        dto.setComment(generateString());
        dto.setBreed(generateString());
        dto.setDistance(generateDouble());
        return dto;
    }

    public static ReportResponse generateReportResponse() {
        var dto = new ReportResponse();
        dto.setOwner(false);
        dto.setId(generateLong());
        dto.setPet(generatePetDto());
        dto.setGeo(generateGeoDto());
        dto.setExtraInfo(generateExtraInfo());
        dto.setUser(generateUserResponseDto());
        dto.setDistance(generateDouble());
        return dto;
    }

    private static UserResponseDto generateUserResponseDto() {
        var dto = new UserResponseDto();
        dto.setEmail(generateEmail());
        dto.setPhone(generatePhone());
        dto.setName(generateString());
        return dto;
    }

    public static CreateReportRequest generateCreateReportRequest() {
        var dto = new CreateReportRequest();
        dto.setExtraInfo(generateExtraInfo());
        dto.setGeo(generateGeoDto());
        dto.setPet(generatePetDto());
        dto.setReportType(ReportType.LOST);
        return dto;
    }

    private static ExtraInfoDto generateExtraInfo() {
        var dto = new ExtraInfoDto();
        dto.setComment(generateString());
        dto.setLostFoundDate(LocalDate.now());
        return dto;
    }

    private static GeoDto generateGeoDto() {
        var dto = new GeoDto();
        dto.setLatitude(generateDouble());
        dto.setLongitude(generateDouble());
        dto.setRadius(generateDouble());
        dto.setDescription(generateString());
        return dto;
    }

    private static PetDto generatePetDto() {
        var dto = new PetDto();
        dto.setName(generateString());
        dto.setType(PetType.CAT);
        dto.setBreed(generateString());
        dto.setPhotos(generateSet(3, CommonGenerator::generateString));
        dto.setTags(generateSet(3, CommonGenerator::generateString));
        return dto;
    }
}