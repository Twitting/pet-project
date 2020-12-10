package ru.twitting.petproject.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.twitting.petproject.builder.UserEntityBuilder;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.service.impl.UserServiceImpl;
import ru.twitting.petproject.test.TestWithSecurityContext;
import ru.twitting.petproject.test.tags.UnitTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.twitting.petproject.test.helper.ControllerHelper.MOCK_USERNAME;
import static ru.twitting.petproject.test.helper.generator.DtoGenerator.generateUserDto;
import static ru.twitting.petproject.test.helper.generator.EntityGenerator.generateUserEntity;

@UnitTest
@DisplayName("UserService Unit test")
class UserServiceTest extends TestWithSecurityContext {

    @Mock
    private UserAccessService userAccessServiceMock;
    @Mock
    private UserEntityBuilder userEntityBuilderMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        initSecurityContext();
        userService = new UserServiceImpl(userAccessServiceMock, userEntityBuilderMock, passwordEncoderMock);
    }

    @AfterEach
    void tearDown() {
        reset(
                userAccessServiceMock,
                userEntityBuilderMock,
                passwordEncoderMock
        );
    }

    //  -------------------------------- POSITIVE TESTS --------------------------------

    @Test
    @DisplayName("createUser(): returns valid response entity on valid request")
    void successfulCreateUser() {
        var entity = generateUserEntity();
        when(userEntityBuilderMock.build(any())).thenReturn(entity);
        when(userAccessServiceMock.save(any())).thenAnswer(it -> it.getArgument(0));

        var actual = assertDoesNotThrow(() -> userService.createUser(generateUserDto()));

        assertNotNull(actual);
        assertEquals(entity.getShownName(), actual.getName());
        verify(userEntityBuilderMock).build(any());
        verify(userAccessServiceMock).save(any());
    }

    @Test
    @DisplayName("getUser(): returns valid response entity on valid request")
    void successfulGetUser() {
        var entity = generateUserEntity();
        entity.setUsername(MOCK_USERNAME);
        when(userAccessServiceMock.findByUsername(any())).thenReturn(entity);

        var actual = assertDoesNotThrow(() -> userService.getUser());

        assertNotNull(actual);
        assertEquals(entity.getShownName(), actual.getName());
        verify(userAccessServiceMock).findByUsername(any());
    }

    @Test
    @DisplayName("changeUser(): returns valid response entity on valid request")
    void successfulChangeUser() {
        var entity = generateUserEntity();
        entity.setUsername(MOCK_USERNAME);
        var dto = generateUserDto();
        dto.setUsername(MOCK_USERNAME);
        when(userAccessServiceMock.findByUsername(any())).thenReturn(entity);
        when(passwordEncoderMock.encode(any())).thenAnswer(it -> it.getArgument(0));
        when(userAccessServiceMock.save(any())).thenAnswer(it -> it.getArgument(0));

        var actual = assertDoesNotThrow(() -> userService.changeUser(dto));

        assertNotNull(actual);
        assertEquals(entity.getShownName(), actual.getName());
        assertEquals(entity.getPhone(), actual.getPhone());
        assertEquals(entity.getEmail(), actual.getEmail());
        verify(userAccessServiceMock).findByUsername(any());
        verify(userAccessServiceMock).save(any());
        verify(passwordEncoderMock).encode(any());
    }
}