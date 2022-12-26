package be.kdg.delidish.business.adapter;

import be.kdg.delidish.business.domain.common.Position;
import org.springframework.stereotype.Component;

@Component
public interface DistanceCalculator {
    double getDistance(Position pos1, Position pos2);
}