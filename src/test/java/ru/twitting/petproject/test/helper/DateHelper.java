package ru.twitting.petproject.test.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateHelper {

    public static String formatToString(LocalDate dateTime) {
        return formatToString(dateTime, DateTimeFormatter.ISO_DATE);
    }

    public static String formatToString(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }

}