package ru.twitting.petproject.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.twitting.petproject.security.service.PetUserDetailsService;

@Component
@RequiredArgsConstructor
public class PetUserAuthenticationProvider implements AuthenticationProvider {

    private final PetUserDetailsService petUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        var name = authentication.getName();
        var password = authentication.getCredentials().toString();

        var user = petUserDetailsService.loadUserByUsername(name);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user, password, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Username or password is wrong");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
