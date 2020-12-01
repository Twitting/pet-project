package ru.twitting.petproject.test.helper.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.dto.ExtraInfoDto;
import ru.twitting.petproject.model.dto.GeoDto;
import ru.twitting.petproject.model.dto.PetDto;
import ru.twitting.petproject.model.dto.UserReportRequestDto;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;

import java.time.LocalDate;

import static ru.twitting.petproject.test.helper.generator.CommonGenerator.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DtoGenerator {

    public static CreateReportRequest generateCreateReportRequest() {
        var dto = new CreateReportRequest();
        dto.setExtraInfo(generateExtraInfo());
        dto.setGeo(generateGeoDto());
        dto.setPet(generatePetDto());
        dto.setUser(generateUserReportDto());
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

    public static UserReportRequestDto generateUserReportDto() {
        var dto = new UserReportRequestDto();
        dto.setName(generateString());
        dto.setEmail(generateEmail());
        dto.setPhone(generatePhone());
        dto.setEmailShown(true);
        dto.setPhoneShown(true);
        dto.setName(generateString());
        dto.setPassword(generateString());
        return dto;
    }
}