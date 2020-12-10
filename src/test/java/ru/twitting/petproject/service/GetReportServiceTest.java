package ru.twitting.petproject.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.service.impl.GetReportServiceImpl;
import ru.twitting.petproject.test.TestWithSecurityContext;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.ControllerHelper.MOCK_USERNAME;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.*;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateReportSearchParamsDto;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateReportEntity;

@UnitTest
@DisplayName("GetReportService Unit test")
class GetReportServiceTest extends TestWithSecurityContext {

    @Mock
    private ReportAccessService reportAccessServiceMock;

    private GetReportService getReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initSecurityContext();
        getReportService = new GetReportServiceImpl(reportAccessServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                reportAccessServiceMock
        );
    }

    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @DisplayName("getReports(): returns valid response entity on valid request")
    void successfulGetReports() {
        when(reportAccessServiceMock.findAllByReportType(any(), any()))
                .thenReturn(generatePageOf(generateReportEntity()));

        var actual = assertDoesNotThrow(() -> getReportService.getReports(generateReportSearchParamsDto(), generatePageable()));

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        verify(reportAccessServiceMock).findAllByReportType(any(), any());
    }

    @Test
    @DisplayName("getUserReports(): returns valid response entity on valid request")
    void successfulGetUserReports() {
        var entity = generateReportEntity();
        entity.getUser().setUsername(MOCK_USERNAME);
        when(reportAccessServiceMock.findAllByUsername(any(), any())).thenReturn(generatePageOf(entity));

        var actual = assertDoesNotThrow(() -> getReportService.getUserReports(generatePageable()));

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        verify(reportAccessServiceMock).findAllByUsername(any(), any());
    }

    @Test
    @DisplayName("getReport(): returns valid response entity on valid request, owner")
    void successfulGetReport() {
        var entity = generateReportEntity();
        entity.getUser().setUsername(MOCK_USERNAME);
        when(reportAccessServiceMock.findById(any())).thenReturn(entity);

        var actual = assertDoesNotThrow(() -> getReportService.getReport(generateLong(), generatePoint()));

        assertNotNull(actual);
        assertTrue(actual.getOwner());
        verify(reportAccessServiceMock).findById(any());
    }

    @Test
    @DisplayName("getReport(): returns valid response entity on valid request, owner")
    void successfulGetReportNotOwner() {
        when(reportAccessServiceMock.findById(any())).thenReturn(generateReportEntity());

        var actual = assertDoesNotThrow(() -> getReportService.getReport(generateLong(), generatePoint()));

        assertNotNull(actual);
        assertFalse(actual.getOwner());
        verify(reportAccessServiceMock).findById(any());
    }

}