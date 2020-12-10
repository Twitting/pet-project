package ru.twitting.petproject.test.helper.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.twitting.petproject.dao.entity.BaseEntity;
import ru.twitting.petproject.dao.entity.ReportEntity;
import ru.twitting.petproject.dao.entity.TagEntity;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.model.base.PetType;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static ru.twitting.petproject.test.helper.generator.CommonGenerator.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityGenerator {

    public static TagEntity generateTagEntity() {
        var entity = new TagEntity();
        entity.setName(generateString());
        return entity;
    }

    public static UserEntity generateUserEntity() {
        var entity = new UserEntity();
        entity.setId(generateLong());
        entity.setPassword(generateString());
        entity.setUsername(generateString());
        entity.setShownName(generateString());
        entity.setPhone(generatePhone());
        entity.setEmail(generateEmail());
        entity.setPhoneShown(true);
        entity.setEmailShown(true);
        setBaseEntityAttributes(entity);
        return entity;
    }

    public static ReportEntity generateReportEntity() {
        var entity = new ReportEntity();
        entity.setId(generateLong());
        entity.setLostFoundDate(LocalDate.now());
        entity.setComment(generateString());
        entity.setGeoDescription(generateString());
        entity.setRadius(generateDouble());
        entity.setGeoLocation(generatePoint());
        entity.setUser(generateUserEntity());
        entity.setPetType(generateOneOf(PetType.CAT, PetType.DOG, PetType.OTHER));
        entity.setPhotos(generateSet(3, CommonGenerator::generateString));
        entity.setTags(generateSet(3, EntityGenerator::generateTagEntity));
        entity.setPetName(generateString());
        entity.setBreed(generateString());
        return entity;
    }

    private static void setBaseEntityAttributes(BaseEntity entity) {
        entity.setActive(true);
        entity.setCreated(OffsetDateTime.now());
        entity.setModified(OffsetDateTime.now());
    }
}