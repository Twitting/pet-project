package ru.twitting.petproject.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.twitting.petproject.dao.access.UserAccessService;
import ru.twitting.petproject.security.model.SecurityUser;

@Service
@RequiredArgsConstructor
public class PetUserDetailsService implements UserDetailsService {

    private final UserAccessService userAccessService;

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        var user = userAccessService.findByUsernameOptional(username)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException(String.format("User with username %s not found", username)));
        return new SecurityUser(user);
    }
}
