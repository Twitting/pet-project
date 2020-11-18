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
import org.springframework.test.util.ReflectionTestUtils;
import ru.twitting.petproject.model.dto.request.CreateReportRequest;
import ru.twitting.petproject.service.CreateReportService;
import ru.twitting.petproject.test.tags.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.AssertionHelper.assertCall;
import static ru.twitting.petproject.test.helper.ControllerHelper.post;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateCreateReportRequest;

@SpringIntegrationTest
@DisplayName("ReportController Integration test")
class ReportControllerTest {

    @LocalServerPort
    private int port;

    @Mock
    private CreateReportService createReportServiceMock;

    @Autowired
    private ReportController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(controller, "createReportService", createReportServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(createReportServiceMock);
    }


    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @DisplayName("createReport(): returns valid response entity on valid request")
    void successfulCreateReport() {
        doNothing().when(createReportServiceMock).createReport(any());

        var actual = assertDoesNotThrow(
                () -> post(
                        restTemplate,
                        generateCreateReportRequest(),
                        "/reports",
                        port

                )
        );
        assertCall().accept(actual, HttpStatus.OK);
        verify(createReportServiceMock, times(1)).createReport(any());
    }

    //  -------------------------------- NEGATIVE TESTS --------------------------------

    @Test
    @DisplayName("createReport(): returns 400 response entity on invalid request")
    void unsuccessfulCreateReport() {
        doNothing().when(createReportServiceMock).createReport(any());
        var invalidRequest = new CreateReportRequest();

        var actual = assertDoesNotThrow(
                () -> post(
                        restTemplate,
                        invalidRequest,
                        "/reports",
                        port

                )
        );
        assertCall().accept(actual, HttpStatus.BAD_REQUEST);
        verify(createReportServiceMock, never()).createReport(any());
    }
}