package ru.twitting.petproject.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.twitting.petproject.exception.ForbiddenException;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserVerificator {

    public static void checkUsername(String username) {
        var context = SecurityContextHolder.getContext();
        if (!Objects.equals(context.getAuthentication().getName(), username)) {
            throw new ForbiddenException(String.format("Not allowed to change user with username %s", username));
        }
    }
}
