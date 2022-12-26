package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.EventType;

public class DeliveryPointEventFactory {
    public static DeliveryPointEvent create(int points, EventType type) {
        return new DeliveryPointEvent(points, type);
    }
}
