package ru.twitting.petproject.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import ru.twitting.petproject.builder.ReportEntityBuilder;
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.test.tags.SpringMockTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateCreateReportRequest;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateReportEntity;

@SpringMockTest
@DisplayName("CreateReportService Mock test")
class CreateReportServiceTest {

    @Mock
    private ReportEntityBuilder reportEntityBuilderMock;
    @Mock
    private ReportAccessService reportAccessServiceMock;


    @Autowired
    private CreateReportService createReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(createReportService, "reportEntityBuilder", reportEntityBuilderMock);
        ReflectionTestUtils.setField(createReportService, "reportAccessService", reportAccessServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                reportEntityBuilderMock,
                reportAccessServiceMock
        );
    }

    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @WithMockUser
    @DisplayName("createReport(): returns valid response entity on valid request")
    void successfulCreateReport() {
        var entity = generateReportEntity();
        when(reportEntityBuilderMock.build(any())).thenReturn(entity);
        when(reportAccessServiceMock.save(any())).thenAnswer(it -> it.getArgument(0));

        var actual = assertDoesNotThrow(() -> createReportService.createReport(generateCreateReportRequest()));

        assertNotNull(actual);
        assertEquals(entity.getPetName(), actual.getPet().getName());
        assertEquals(entity.getGeoDescription(), actual.getGeo().getDescription());
        verify(reportEntityBuilderMock).build(any());
        verify(reportAccessServiceMock).save(any());
    }

}