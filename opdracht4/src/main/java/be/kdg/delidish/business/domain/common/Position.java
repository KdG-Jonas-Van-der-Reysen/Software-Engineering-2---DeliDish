package be.kdg.delidish.business.domain.common;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceCalculator;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.impl.PointImpl;


public class Position {

    private double longitude;
    private double lattitude;

    public Position(double longitude, double lattitude) {
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    // Method to calculate distance in km between two positions
    public double calculateDistance(Position position) {
        // Create a SpatialContext object
        SpatialContext ctx = SpatialContext.GEO;

        // Create Point objects for the two points
        Point point1 = new PointImpl(longitude, lattitude, ctx);
        Point point2 = new PointImpl(position.longitude, position.lattitude, ctx);

        // Get a DistanceCalculator from the SpatialContext
        DistanceCalculator distCalc = ctx.getDistCalc();

        // Calculate the distance between the two points in degrees
        double distance = distCalc.distance(point1, point2) * DistanceUtils.DEG_TO_KM;
        System.out.println(distance);

        return distance;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}