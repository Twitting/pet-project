//package ru.twitting.petproject.security.authentication;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//import ru.twitting.petproject.dao.access.UserAccessService;
//
//@Component
//@RequiredArgsConstructor
//public class PetUserAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserAccessService userAccessService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        var name = authentication.getName();
//        var password = authentication.getCredentials().toString();
//
//        try {
//            var user = userAccessService.findByNameAndPassword(name, password);
//            var userDetails = User.builder()
//                    .username(user.getName())
//                    .password(user.getPassword())
//                    .roles("USER")
//                    .build();
//            return new UsernamePasswordAuthenticationToken(
//                    userDetails, password, userDetails.getAuthorities());
//        } catch (Exception e) {
//            throw new AuthenticationCredentialsNotFoundException(e.getMessage());
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
