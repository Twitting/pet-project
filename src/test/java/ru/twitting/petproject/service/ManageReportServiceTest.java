package ru.twitting.petproject.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.twitting.petproject.builder.ReportEntityBuilder;
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.service.impl.ManageReportServiceImpl;
import ru.twitting.petproject.test.TestWithSecurityContext;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.ControllerHelper.MOCK_USERNAME;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generateLong;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateCreateReportRequest;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateReportEntity;

@UnitTest
@DisplayName("ManageReportService Unit test")
class ManageReportServiceTest extends TestWithSecurityContext {

    @Mock
    private ReportEntityBuilder reportEntityBuilderMock;
    @Mock
    private ReportAccessService reportAccessServiceMock;

    private ManageReportService manageReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initSecurityContext();
        manageReportService = new ManageReportServiceImpl(reportEntityBuilderMock, reportAccessServiceMock);
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
    @DisplayName("createReport(): returns valid response entity on valid request")
    void successfulCreateReport() {
        var entity = generateReportEntity();
        when(reportEntityBuilderMock.build(any())).thenReturn(entity);
        when(reportAccessServiceMock.save(any())).thenAnswer(it -> it.getArgument(0));

        var actual = assertDoesNotThrow(() -> manageReportService.createReport(generateCreateReportRequest()));

        assertNotNull(actual);
        assertEquals(entity.getPetName(), actual.getPet().getName());
        assertEquals(entity.getGeoDescription(), actual.getGeo().getDescription());
        verify(reportEntityBuilderMock).build(any());
        verify(reportAccessServiceMock).save(any());
    }

    @Test
    @DisplayName("closeReport(): returns valid response entity on valid request")
    void successfulCloseReport() {
        var entity = generateReportEntity();
        entity.getUser().setUsername(MOCK_USERNAME);
        when(reportAccessServiceMock.findById(any())).thenReturn(entity);
        doNothing().when(reportAccessServiceMock).delete(any());

        assertDoesNotThrow(() -> manageReportService.closeReport(generateLong()));

        verify(reportAccessServiceMock).findById(any());
        verify(reportAccessServiceMock).delete(any());
    }

}