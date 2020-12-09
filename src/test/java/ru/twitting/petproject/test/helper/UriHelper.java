package ru.twitting.petproject.test.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static ru.twitting.petproject.test.helper.CollectionHelper.toMultiValueMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UriHelper {

    private static final String BASE_SCHEMA = "http";
    private static final String BASE_HOST = "localhost";

    public static String generateUri(String path, int port) {
        return UriComponentsBuilder.newInstance()
                .scheme(BASE_SCHEMA)
                .host(BASE_HOST)
                .port(port)
                .path(path)
                .build().toString();
    }

    @SafeVarargs
    public static String generateUri(String path, int port, Map.Entry<String, String>... queryParams) {
        return UriComponentsBuilder.newInstance()
                .scheme(BASE_SCHEMA)
                .host(BASE_HOST)
                .queryParams(toMultiValueMap(queryParams))
                .port(port)
                .path(path)
                .build().toString();
    }
}