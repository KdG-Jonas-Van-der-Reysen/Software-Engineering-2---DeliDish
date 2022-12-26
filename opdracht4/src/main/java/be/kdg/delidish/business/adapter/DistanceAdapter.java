package be.kdg.delidish.business.adapter;

import be.kdg.delidish.business.domain.common.Position;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Point;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.springframework.stereotype.Component;

@Component
public class DistanceAdapter implements DistanceCalculator {

    @Override
    public double getDistance(Position pos1, Position pos2) {
        // Create a SpatialContext object
        SpatialContext ctx = SpatialContext.GEO;

        // Create Point objects for the two points
        Point point1 = new PointImpl(pos1.getLattitude(), pos1.getLongitude(), ctx);
        Point point2 = new PointImpl(pos2.getLattitude(), pos2.getLongitude(), ctx);

        // Get a DistanceCalculator from the SpatialContext
        org.locationtech.spatial4j.distance.DistanceCalculator distCalc = ctx.getDistCalc();

        // Calculate the distance between the two points in degrees and convert to Km
        return distCalc.distance(point1, point2) * DistanceUtils.DEG_TO_KM;
    }
}
