package ru.twitting.petproject.rest.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;
import ru.twitting.petproject.model.base.PetType;
import ru.twitting.petproject.model.base.ReportType;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.service.GetReportService;
import ru.twitting.petproject.service.ManageReportService;
import ru.twitting.petproject.test.tags.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.AssertionHelper.assertCall;
import static ru.twitting.petproject.test.helper.CollectionHelper.entry;
import static ru.twitting.petproject.test.helper.ControllerHelper.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generateLong;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generatePageOf;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.*;

@SpringIntegrationTest
@DisplayName("ReportController Integration test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/fill.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/clean.sql")
class ReportControllerTest {

    @LocalServerPort
    private int port;

    @Mock
    private ManageReportService manageReportServiceMock;
    @Mock
    private GetReportService getReportServiceMock;

    @Autowired
    private ReportController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(controller, "manageReportService", manageReportServiceMock);
        ReflectionTestUtils.setField(controller, "getReportService", getReportServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                manageReportServiceMock,
                getReportServiceMock
        );
    }

    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @DisplayName("createReport(): returns valid response entity on valid request")
    void successfulCreateReport() {

        when(manageReportServiceMock.createReport(any())).thenReturn(generateReportResponse());

        var actual = assertDoesNotThrow(
                () -> post(
                        restTemplate.withBasicAuth(MOCK_USERNAME, MOCK_PASSWORD),
                        generateCreateReportRequest(),
                        "/reports",
                        port

                )
        );
        assertCall().accept(actual, HttpStatus.OK);
        verify(manageReportServiceMock).createReport(any());
    }

    @Test
    @DisplayName("getReports(): returns valid response entity on valid request")
    void successfulGetReports() {

        when(getReportServiceMock.getReports(any(), any())).thenReturn(generatePageOf(generateShortReportResponse()));

        var actual = assertDoesNotThrow(
                () -> get(
                        restTemplate.withBasicAuth(MOCK_USERNAME, MOCK_PASSWORD),
                        "/reports",
                        port,
                        entry("reportType", ReportType.LOST.name()),
                        entry("petType", PetType.CAT.name())
                )
        );
        assertCall().accept(actual, HttpStatus.OK);
        verify(getReportServiceMock).getReports(any(), any());
    }

    @Test
    @DisplayName("getUserReports(): returns valid response entity on valid request")
    void successfulGetUserReports() {

        when(getReportServiceMock.getUserReports(any())).thenReturn(generatePageOf(generateShortReportResponse()));

        var actual = assertDoesNotThrow(
                () -> get(
                        restTemplate.withBasicAuth(MOCK_USERNAME, MOCK_PASSWORD),
                        "/reports/my",
                        port
                )
        );
        assertCall().accept(actual, HttpStatus.OK);
        verify(getReportServiceMock).getUserReports(any());
    }

    @Test
    @DisplayName("getReport(): returns valid response entity on valid request")
    void successfulGetReport() {

        when(getReportServiceMock.getReport(any(), any())).thenReturn(generateReportResponse());

        var actual = assertDoesNotThrow(
                () -> get(
                        restTemplate.withBasicAuth(MOCK_USERNAME, MOCK_PASSWORD),
                        "/reports/" + generateLong(),
                        port

                )
        );
        assertCall().accept(actual, HttpStatus.OK);
        verify(getReportServiceMock).getReport(any(), any());
    }

    @Test
    @DisplayName("closeReport(): returns valid response entity on valid request")
    void successfulCloseReport() {

        doNothing().when(manageReportServiceMock).closeReport(any());

        var actual = assertDoesNotThrow(
                () -> delete(
                        restTemplate.withBasicAuth(MOCK_USERNAME, MOCK_PASSWORD),
                        null,
                        "/reports/" + generateLong(),
                        port

                )
        );
        assertCall().accept(actual, HttpStatus.OK);
        verify(manageReportServiceMock).closeReport(any());
    }

    //  -------------------------------- NEGATIVE TESTS --------------------------------

    @Test
    @DisplayName("createReport(): returns 400 response entity on invalid request")
    void unsuccessfulCreateReport() {
        var invalidRequest = new CreateReportRequest();

        var actual = assertDoesNotThrow(
                () -> post(
                        restTemplate.withBasicAuth(MOCK_USERNAME, MOCK_PASSWORD),
                        invalidRequest,
                        "/reports",
                        port

                )
        );
        assertCall().accept(actual, HttpStatus.BAD_REQUEST);
        verify(manageReportServiceMock, never()).createReport(any());
    }
}