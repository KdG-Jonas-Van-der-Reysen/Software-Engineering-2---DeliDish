package be.kdg.delidish.business.factory.person;

import be.kdg.delidish.business.adapter.DistanceAdapter;
import be.kdg.delidish.business.adapter.DistanceCalculator;
import be.kdg.delidish.business.domain.common.Position;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.DeliveryPointEvent;

import java.util.List;

public class CourierFactory {
    private final static DistanceCalculator distanceCalculator = new DistanceAdapter();

    public static Courier create(String firstName, String lastName, List<DeliveryPointEvent> deliveryPointEvents, boolean isAvailable, Position currentPosition) {
        return new Courier(firstName, lastName, deliveryPointEvents, isAvailable, currentPosition, distanceCalculator);
    }
}
