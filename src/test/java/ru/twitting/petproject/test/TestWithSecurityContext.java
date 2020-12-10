package ru.twitting.petproject.test;

import org.mockito.Mock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.when;
import static ru.twitting.petproject.test.helper.ControllerHelper.MOCK_USERNAME;

public class TestWithSecurityContext {

    @Mock
    private Authentication authenticationMock;
    @Mock
    private SecurityContext securityContextMock;

    public void initSecurityContext() {
        SecurityContextHolder.setContext(securityContextMock);
        when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        when(authenticationMock.getName()).thenReturn(MOCK_USERNAME);
    }
}
