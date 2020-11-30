package ru.twitting.petproject.test.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CollectionHelper {

    public static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    @SafeVarargs
    public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map.Entry<K, V>... queryParams) {
        return Stream.of(queryParams)
                .collect(
                        Collectors.toMap(Map.Entry::getKey, v -> List.of(v.getValue()), throwingMerger(), LinkedMultiValueMap::new)
                );
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

}