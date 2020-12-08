package ru.twitting.petproject.test.helper.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.dto.ExtraInfoDto;
import ru.twitting.petproject.model.dto.GeoDto;
import ru.twitting.petproject.model.dto.PetDto;
import ru.twitting.petproject.model.dto.UserResponseDto;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.model.dto.response.ReportResponse;
import ru.twitting.petproject.security.model.SecurityUser;

import java.time.LocalDate;

import static ru.twitting.petproject.test.helper.generator.CommonGenerator.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DtoGenerator {

    public static ReportResponse generateReportResponse() {
        var dto = new ReportResponse();
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