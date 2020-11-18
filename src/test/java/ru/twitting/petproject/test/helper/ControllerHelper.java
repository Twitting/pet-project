package ru.twitting.petproject.test.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ControllerHelper {

    @SafeVarargs
    public static <T> ResponseEntity<T> get(TestRestTemplate restTemplate, Class<T> responseType, String path,
                                            int port, Map.Entry<String, String>... queryParams) {
        return execute(restTemplate, path, port, responseType, queryParams);
    }

    public static <T, R> ResponseEntity<T> post(TestRestTemplate restTemplate, R request, String path, int port) {
        return exchange(restTemplate, request, HttpMethod.POST, path, port);
    }

    public static <T, R> ResponseEntity<T> patch(TestRestTemplate restTemplate, R request, String path, int port) {
        return exchange(restTemplate, request, HttpMethod.PATCH, path, port);
    }

    public static <T, R> ResponseEntity<T> put(TestRestTemplate restTemplate, R request, String path, int port) {
        return exchange(restTemplate, request, HttpMethod.PUT, path, port);
    }

    public static <T, R> ResponseEntity<T> delete(TestRestTemplate restTemplate, R request, String path, int port) {
        return exchange(restTemplate, request, HttpMethod.DELETE, path, port);
    }

    private static <T, R> ResponseEntity<T> exchange(TestRestTemplate restTemplate, R request,
                                                     HttpMethod method, String path, int port) {
        var uri = UriHelper.generateUri(path, port);
        return restTemplate.exchange(uri, method, new HttpEntity<>(request), new ParameterizedTypeReference<>() {
        });
    }

    @SafeVarargs
    private static <T> ResponseEntity<T> execute(TestRestTemplate restTemplate, String path,
                                                 int port, Class<T> responseType,
                                                 Map.Entry<String, String>... queryParams) {
        var uri = UriHelper.generateUri(path, port, queryParams);
        return restTemplate.getForEntity(uri, responseType);
    }
}