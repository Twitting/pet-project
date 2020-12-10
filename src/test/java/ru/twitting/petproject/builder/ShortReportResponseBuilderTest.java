package ru.twitting.petproject.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generatePoint;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateReportEntity;

@UnitTest
@DisplayName("ShortReportResponseBuilder Unit test")
class ShortReportResponseBuilderTest {

    //  ------------------------------------------------ POSITIVE TESTS ------------------------------------------------

    @Test
    @DisplayName("build(): returns valid response entity on valid request, no point")
    void successfulBuild() {
        var entity = generateReportEntity();

        var actual = assertDoesNotThrow(() -> new ShortReportResponseBuilder(entity).build());

        assertNotNull(actual);
        assertEquals(entity.getId(), actual.getId());
        assertEquals(entity.getBreed(), actual.getBreed());
        assertEquals(entity.getComment(), actual.getComment());
        assertEquals(entity.getGeoLocation().getY(), actual.getLatitude());
        assertEquals(entity.getGeoLocation().getX(), actual.getLongitude());
        assertEquals(entity.getPetType(), actual.getType());
        assertEquals(entity.getSex(), actual.getSex());
        assertNull(actual.getDistance());
    }

    @Test
    @DisplayName("build(): returns valid response entity on valid request, with point")
    void successfulBuildPoint() {
        var entity = generateReportEntity();
        var point = generatePoint();

        var actual = assertDoesNotThrow(() -> new ShortReportResponseBuilder(entity, point).build());

        assertNotNull(actual);
        assertEquals(entity.getId(), actual.getId());
        assertEquals(entity.getBreed(), actual.getBreed());
        assertEquals(entity.getComment(), actual.getComment());
        assertEquals(entity.getGeoLocation().getY(), actual.getLatitude());
        assertEquals(entity.getGeoLocation().getX(), actual.getLongitude());
        assertEquals(entity.getPetType(), actual.getType());
        assertEquals(entity.getSex(), actual.getSex());
        assertNotNull(actual.getDistance());
    }
}