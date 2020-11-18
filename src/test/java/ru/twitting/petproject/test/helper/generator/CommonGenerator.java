package ru.twitting.petproject.test.helper.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.*;
import ru.twitting.petproject.util.PointUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonGenerator {

    private static final int DEFAULT_WORD_LENGTH = 20;
    private static final Long DEFAULT_MAX_LONG = Long.MAX_VALUE;

    public static Long generateLong() {
        return generateLong(DEFAULT_MAX_LONG);
    }

    public static Long generateLong(Long rightLimit) {
        var leftLimit = 1L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public static Double generateDouble() {
        var leftLimit = 1;
        var rightLimit = Double.MAX_VALUE;
        return leftLimit + (Math.random() * (rightLimit - leftLimit));
    }

    public static BigDecimal generateDecimal() {
        return BigDecimal.valueOf(new Random(System.currentTimeMillis()).nextDouble());
    }

    public static String generateString() {
        return generateString(DEFAULT_WORD_LENGTH);
    }

    public static String generateString(long length) {
        var leftLimit = 48;
        var rightLimit = 122;

        return new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> i <= 57 || i >= 65)
                .filter(i -> i <= 90 || i >= 97)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String generatePhone() {
        return "+79".concat(String.valueOf(generateLong(999999999L)));
    }

    public static String generateEmail() {
        return generateString()
                .concat("@")
                .concat(generateString())
                .concat(".")
                .concat(generateString(3L));
    }

    public static Integer generateInteger() {
        return Math.abs(new Random().nextInt());
    }

    public static Integer generateInteger(Integer min, Integer max) {
        return new Random().nextInt(max + 1 - min) + min;
    }

    public static <T> Page<T> generatePage(List<T> content) {
        return new PageImpl<>(content);
    }

    public static Pageable generatePageable(Integer page, Integer size, Sort sort) {
        if (!Objects.nonNull(sort)) {
            sort = Sort.unsorted();
        }
        return PageRequest.of(page, size, sort);
    }

    public static <T> Set<T> generateSet(Integer count, Supplier<T> function) {
        var list = new HashSet<T>();
        for (int i = 0; i < count; i++) {
            list.add(function.get());
        }
        return list;
    }

    public static <T> List<T> generateList(Integer count, Supplier<T> function) {
        var list = new ArrayList<T>();
        for (int i = 0; i < count; i++) {
            list.add(function.get());
        }
        return list;
    }

    public static Point generatePoint(Double latitude, Double longitude) {
        return PointUtils.ofPostGis(latitude, longitude);
    }

    public static <T> T generateOneOf(T ... objects) {
        var quantity = objects.length;
        return objects[generateInteger(0, quantity)];
    }
}
