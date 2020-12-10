package ru.twitting.petproject.builder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateUserDto;

@UnitTest
@DisplayName("UserEntityBuilder Unit test")
class UserEntityBuilderTest {

    @Mock
    private PasswordEncoder passwordEncoderMock;
    @Mock
    private UserAccessService userAccessServiceMock;

    private UserEntityBuilder builder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        builder = new UserEntityBuilder(passwordEncoderMock, userAccessServiceMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                passwordEncoderMock,
                userAccessServiceMock
        );
    }

    //  ------------------------------------------------ POSITIVE TESTS ------------------------------------------------

    @Test
    @DisplayName("build(): returns valid response entity on valid request, user in db")
    void successfulBuild() {
        var dto = generateUserDto();
        when(passwordEncoderMock.encode(any()))
                .thenAnswer(it -> it.getArgument(0));
        doNothing().when(userAccessServiceMock).checkIfUserExist(any());

        var actual = assertDoesNotThrow(() -> builder.build(dto));

        assertNotNull(actual);
        assertEquals(dto.getUsername(), actual.getUsername());
        assertEquals(dto.getEmail(), actual.getEmail());
        assertEquals(dto.getPhone(), actual.getPhone());
        verify(passwordEncoderMock).encode(any());
        verify(userAccessServiceMock).checkIfUserExist(any());
    }


}