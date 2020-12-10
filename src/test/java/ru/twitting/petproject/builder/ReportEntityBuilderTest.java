package ru.twitting.petproject.builder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.twitting.petproject.dao.access.TagAccessService;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.test.TestWithSecurityContext;
import ru.twitting.petproject.test.helper.generator.EntityGenerator;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generateSet;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateCreateReportRequest;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateUserEntity;

@UnitTest
@DisplayName("ReportEntityBuilder Unit test")
class ReportEntityBuilderTest extends TestWithSecurityContext {

    @Mock
    private TagAccessService tagAccessServiceMock;
    @Mock
    private UserAccessService userAccessServiceMock;

    private ReportEntityBuilder builder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initSecurityContext();
        builder = new ReportEntityBuilder(tagAccessServiceMock, userAccessServiceMock);
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
                .thenReturn(generateUserEntity());

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