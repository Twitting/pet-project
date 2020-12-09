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
import ru.twitting.petproject.dao.access.ReportAccessService;
import ru.twitting.petproject.test.tags.SpringMockTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.*;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateReportSearchParamsDto;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateReportEntity;

@SpringMockTest
@DisplayName("GetReportService Mock test")
class GetReportServiceTest {

    public static final String MOCK_USERNAME = "user";
    @Mock
    private ReportAccessService reportAccessServiceMock;

    @Autowired
    private GetReportService getReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(getReportService, "reportAccessService", reportAccessServiceMock);
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
    @WithMockUser(username = MOCK_USERNAME)
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
    @WithMockUser(username = MOCK_USERNAME)
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
    @WithMockUser(username = MOCK_USERNAME)
    @DisplayName("getReport(): returns valid response entity on valid request, owner")
    void successfulGetReportNotOwner() {
        when(reportAccessServiceMock.findById(any())).thenReturn(generateReportEntity());

        var actual = assertDoesNotThrow(() -> getReportService.getReport(generateLong(), generatePoint()));

        assertNotNull(actual);
        assertFalse(actual.getOwner());
        verify(reportAccessServiceMock).findById(any());
    }

}