package ru.twitting.petproject.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.modelmapper.internal.util.Assert;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PointUtils {

    /**
     * A spatial reference system (SRS) or coordinate reference system (CRS) is a coordinate-based local for GPS
     *
     * @see <a href="https://en.wikipedia.org/wiki/Spatial_reference_system">More details</a>
     */
    private static final int GPS_SRID = 4326;
    private static final int EARTH_RADIUS_METERS = 6371302;

    /**
     * Constructs a <code>Point</code> with the given coordinate and with a {@link #GPS_SRID}
     *
     * @param latitude is a geographic coordinate that specifies the north–south position of a point on the Earth's surface.
     * @param longitude is a geographic coordinate that specifies the east–west position of a point on the Earth's surface.
     *
     * @return {@link Point}
     *
     * @see <a href="https://stackoverflow.com/a/21299968">Can find more yourself</a>
     */
    public static Point ofPostGis(Double latitude, Double longitude) {
        nonNull(latitude, longitude);
        return getGeomFactory().createPoint(new Coordinate(longitude, latitude));
    }

    public static boolean hasCoordinates(Double latitude, Double longitude) {
        return Objects.nonNull(latitude) && Objects.nonNull(longitude);
    }

    /**
     * Calculate distance between two points in latitude and longitude
     *
     * @param fromLatitude is started latitude point
     * @param toLatitude is the end latitude point
     * @param fromLongitude is starter longitude point
     * @param toLongitude is the end longitude point
     *
     * @return Distance in Meters
     *
     * @see <a href="https://stackoverflow.com/a/16794680">Can find more yourself</a>
     */
    public static Double distance(Double fromLatitude, Double toLatitude, Double fromLongitude, Double toLongitude) {
        if (hasCoordinates(fromLatitude, fromLongitude) && hasCoordinates(toLatitude, toLongitude)) {
            var latDistance = Math.toRadians(toLatitude - fromLatitude);
            var lonDistance = Math.toRadians(toLongitude - fromLongitude);
            var a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(fromLatitude)) * Math.cos(Math.toRadians(toLatitude))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            var distance = EARTH_RADIUS_METERS * c;

            distance = Math.pow(distance, 2);

            return Math.sqrt(distance);
        } else {
            return null;
        }
    }

    private static void nonNull(Double latitude, Double longitude) {
        Assert.notNull(latitude);
        Assert.notNull(longitude);
    }

    private static GeometryFactory getGeomFactory() {
        return new GeometryFactory(new PrecisionModel(), GPS_SRID);
    }
}
