package be.kdg.delidish.business.factory.common;

import be.kdg.delidish.business.domain.common.Position;

public class PositionFactory {
    public static Position create(double lattitude, double longitude) {
        return new Position(lattitude, longitude);
    }
}
