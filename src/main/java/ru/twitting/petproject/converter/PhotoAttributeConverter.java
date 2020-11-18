package ru.twitting.petproject.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j(topic = "PET-CONVERTER")
@RequiredArgsConstructor
public class PhotoAttributeConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return attribute.stream().collect(Collectors.joining(","));
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return Set.of(dbData.split(","));
    }
}
