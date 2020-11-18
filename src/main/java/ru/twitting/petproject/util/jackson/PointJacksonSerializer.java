package ru.twitting.petproject.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;

import java.io.IOException;
import java.util.Objects;

@Slf4j(topic = "PET-UTIL")
public final class PointJacksonSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point point, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String value = null;
        try {
            if (Objects.nonNull(point)) {
                double lat = point.getY();
                double lon = point.getX();
                value = String.format("%s, %s", lat, lon);
            }
        } catch (Exception e) {
            LOGGER.error("An unknown error of a formatting json Point", e);
        }
        gen.writeString(value);
    }
}