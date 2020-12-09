package ru.twitting.petproject.builder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import ru.twitting.petproject.dao.access.TagAccessService;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.test.helper.generator.EntityGenerator;
import ru.twitting.petproject.test.tags.SpringIntegrationTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generateSet;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateCreateReportRequest;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateUserEntity;

@SpringIntegrationTest
@DisplayName("ReportEntityBuilder Integration test")
class ReportEntityBuilderTest {

    @Mock
    private TagAccessService tagAccessServiceMock;
    @Mock
    private UserAccessService userAccessServiceMock;

    @Autowired
    private ReportEntityBuilder builder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(builder, "tagAccessService", tagAccessServiceMock);
        ReflectionTestUtils.setField(builder, "userAccessService", userAccessServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                tagAccessServiceMock,
                userAccessServiceMock
        );
    }

    //  ------------------------------------------------ POSITIVE TESTS ------------------------------------------------

    @Test
    @DisplayName("createReport(): returns valid response entity on valid request, user in db")
    void successfulCreateReportExistUser() {
        when(tagAccessServiceMock.findOrCreateByNames(any()))
                .thenReturn(generateSet(3, EntityGenerator::generateTagEntity));
        when(userAccessServiceMock.findByUsername(any()))
                .thenReturn(Optional.of(generateUserEntity()));

        var actual = assertDoesNotThrow(() -> builder.build(generateCreateReportRequest()));

        assertNotNull(actual);
        assertNotNull(actual.getGeoLocation());
        assertNotNull(actual.getLostFoundDate());
        assertNotNull(actual.getTags());
        assertNotNull(actual.getPetType());
        verify(tagAccessServiceMock).findOrCreateByNames(any());
        verify(userAccessServiceMock).findByUsername(any());
    }

    //  ------------------------------------------------ NEGATIVE TESTS ------------------------------------------------

    @Test
    @DisplayName("createReport(): throws exception on null argument")
    void unsuccessfulCreateReportNull() {

        assertThrows(IllegalArgumentException.class, () -> builder.build(null));

        verify(tagAccessServiceMock, never()).findOrCreateByNames(any());
        verify(userAccessServiceMock, never()).findByUsername(any());

    }
}