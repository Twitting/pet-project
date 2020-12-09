package ru.twitting.petproject.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import ru.twitting.petproject.dao.access.TagAccessService;
import ru.twitting.petproject.test.helper.generator.CommonGenerator;
import ru.twitting.petproject.test.tags.SpringMockTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.generator.CommonGenerator.*;

@SpringMockTest
@DisplayName("GetTagService Mock test")
class GetTagServiceTest {

    @Mock
    private TagAccessService tagAccessServiceMock;

    @Autowired
    private GetTagService getTagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(getTagService, "tagAccessService", tagAccessServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                tagAccessServiceMock
        );
    }

    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @DisplayName("getTags(): returns valid response entity on valid request")
    void successfulGetTags() {
        when(tagAccessServiceMock.findAllNames(any()))
                .thenReturn(generatePage(generateList(3, CommonGenerator::generateString)));

        var actual = assertDoesNotThrow(() -> getTagService.getTags(generatePageable()));

        assertNotNull(actual);
        verify(tagAccessServiceMock).findAllNames(any());
    }

}