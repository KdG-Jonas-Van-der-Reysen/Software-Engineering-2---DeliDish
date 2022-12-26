package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.order.OrderEvent;
import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.EventType;

import java.time.LocalDateTime;

public class OrderEventFactory {
    public static OrderEvent create(DeliveryPointEvent deliveryPointEvent, LocalDateTime time, EventType state, String remark) {
        return new OrderEvent(deliveryPointEvent, time, state, remark);
    }
}
