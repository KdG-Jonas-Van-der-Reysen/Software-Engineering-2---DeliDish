package be.kdg.delidish.business.domain.observer;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.person.Courier;

public class OrderStateObserver implements Observer {

    private final Courier courier;

    public OrderStateObserver(Courier courier) {
        this.courier = courier;
    }

    @Override
    public void update(Observable observable){
        // Wat moeten we nu eigenlijk doen in deze methode?

        // Deze methode dient ervoor om op basis van de veranderde order state een deliveryPointEvent
        // toe te kennen aan de gekoppelde koerier

        /** Enkele testgegevens
         * EventType eventType = EventType.LATE_PICKUP;
         * DeliveryPointEvent deliveryEvent = DeliveryPointEventFactory.create(eventType);
         * trigger: order.addEvent(OrderEventFactory.create(deliveryEvent, LocalDateTime.now(), eventType, "")); --> onze trigger
         * wat er moet gebeuren: courier.addPointEvent(deliveryEvent);
         */


        if(observable instanceof Order) {
            courier.addPointEvent(((Order) observable).getCurrentState().getState());
        }
    }
}
