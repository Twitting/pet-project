package ru.twitting.petproject.test.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AssertionHelper {

    public static BiConsumer<ResponseEntity<?>, HttpStatus> assertCall() {
        return (response, status) ->
                assertAll(
                        () -> assertNotNull(response),
                        () -> assertEquals(status, response.getStatusCode())
                );
    }

}