package be.kdg.delidish.business.factory.person;

import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.EventType;

public class DeliveryPointEventFactory {
    public static DeliveryPointEvent create(EventType type) {
        return new DeliveryPointEvent(type);
    }
}
