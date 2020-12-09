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
import ru.twitting.petproject.service.GetTagService;
import ru.twitting.petproject.test.helper.generator.CommonGenerator;
import ru.twitting.petproject.test.tags.SpringIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.AssertionHelper.assertCall;
import static ru.twitting.petproject.test.helper.ControllerHelper.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generateList;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.generatePage;

@SpringIntegrationTest
@DisplayName("TagController Integration test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/fill.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/clean.sql")
class TagControllerTest {

    @LocalServerPort
    private int port;

    @Mock
    private GetTagService getTagServiceMock;

    @Autowired
    private TagController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(controller, "getTagService", getTagServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                getTagServiceMock
        );
    }

    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @DisplayName("getPopularTags(): returns valid response entity on valid request")
    void successfulGetPopularTags() {

        when(getTagServiceMock.getTags(any())).thenReturn(generatePage(generateList(3, CommonGenerator::generateString)));

        var actual = assertDoesNotThrow(
                () -> get(
                        restTemplate.withBasicAuth(MOCK_USERNAME, MOCK_PASSWORD),
                        "/tags",
                        port

                )
        );
        assertCall().accept(actual, HttpStatus.OK);
        verify(getTagServiceMock).getTags(any());
    }

    //  -------------------------------- NEGATIVE TESTS --------------------------------

}