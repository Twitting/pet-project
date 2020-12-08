package ru.twitting.petproject.test.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.DatatypeConverter;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ControllerHelper {

    private static final String TEST_CREDENTIALS = "username:pwd";
    public static final String TEST_AUTH_HEADER = "Basic " + DatatypeConverter.printBase64Binary((TEST_CREDENTIALS).getBytes());

    public static <T> ResponseEntity<T> get(TestRestTemplate restTemplate, String path,
                                            int port, Map.Entry<String, String>... queryParams) {
        return exchange(restTemplate, null, HttpMethod.GET, path, port, queryParams);
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
                                                     HttpMethod method, String path, int port,
                                                     Map.Entry<String, String>... queryParams) {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, TEST_AUTH_HEADER);
        var uri = UriHelper.generateUri(path, port, queryParams);
        return restTemplate.exchange(uri, method, new HttpEntity<>(request, headers), new ParameterizedTypeReference<>() {
        });
    }
}