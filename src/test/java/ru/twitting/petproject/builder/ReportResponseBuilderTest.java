package ru.twitting.petproject.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import ru.twitting.petproject.test.TestWithSecurityContext;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generatePoint;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateReportEntity;

@UnitTest
@DisplayName("ReportResponseBuilder Unit test")
class ReportResponseBuilderTest extends TestWithSecurityContext {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initSecurityContext();
    }

    //  ------------------------------------------------ POSITIVE TESTS ------------------------------------------------

    @Test
    @DisplayName("build(): returns valid response entity on valid request, no point")
    void successfulBuildNoPoint() {
        var entity = generateReportEntity();

        var actual = assertDoesNotThrow(() -> new ReportResponseBuilder(entity).build());

        assertNotNull(actual);
        assertEquals(entity.getId(), actual.getId());
        assertNull(actual.getDistance());
    }

    @Test
    @DisplayName("build(): returns valid response entity on valid request, no point")
    void successfulBuildPoint() {
        var entity = generateReportEntity();
        var point = generatePoint();

        var actual = assertDoesNotThrow(() -> new ReportResponseBuilder(entity, point).build());

        assertNotNull(actual);
        assertEquals(entity.getId(), actual.getId());
        assertNotNull(actual.getDistance());
    }
}