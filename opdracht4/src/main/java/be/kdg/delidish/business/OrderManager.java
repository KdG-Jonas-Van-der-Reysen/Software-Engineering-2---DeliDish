package be.kdg.delidish.business;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderEvent;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.EventType;
import be.kdg.delidish.repositories.memory.CourierMemoryRepository;
import be.kdg.delidish.repositories.memory.OrderMemoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderManager {

    private final OrderMemoryRepository orderRepository;
    private final CourierMemoryRepository courierRepository;

    OrderManager(OrderMemoryRepository orderRepository, CourierMemoryRepository courierRepository) {
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
    }

    public void assignOrder(int orderId, int courierId, LocalDateTime ldt) {
        Order order = orderRepository.findById(orderId);
        Courier courier = courierRepository.findById(courierId);

        if (courier != null && order != null) {
            // Register the courier
            order.assignCourier(courier);

            // Set the right state
            order.setState(OrderState.ACCEPTED_BY_COURIER);

            // Add the order event
            OrderEvent oe = new OrderEvent(
                    new DeliveryPointEvent(50, EventType.ORDER_ACCEPTED),
                    ldt,
                    EventType.ORDER_ACCEPTED,
                    "The order has been accepted by a courier"
            );

            order.addEvent(oe);

            // Update the order
            orderRepository.update(order.getOrderId(), order);
        }
    }

    public List<Order> getAllOrders() {
        //TODO Taak 2/ Code punt 1; alle orders kunnen laten zien
        return null;
    }

    public List<Order> getAvailableOrders(int courierId) {
        // Eerst alle orders opvragen en filteren op status ORDER_PLACED --> Dat zijn alle orders die nog geleverd moeten worden
        List<Order> orders = orderRepository.getAll().stream().filter(order -> order.getState() == OrderState.ORDER_PLACED).toList();
        assert !orders.isEmpty();

        Courier courier = courierRepository.findById(courierId);
        if (courier == null) {   //if the c<ourier is invalid return empty list
            return orders;
        }

        // Per order
        // Lijst ophalen van in aanmerking komende koeriers per order (getApplicableCouriersForOrder)
        return orders.stream().filter(order -> {
            List<Courier> applicableCouriers = getApplicableCouriersForOrder(order);
            // --> Zit onze koerier erbij?
            if (applicableCouriers.contains(courier)) {
                // Hoe lang geleden is het order geplaatst?
                // Langer dan 5 min geleden --> order is available voor deze koerier
                if (order.getTimePlaced().isBefore(LocalDateTime.now().minusMinutes(5))) {
                    return true;
                } else {
                    // gemiddelde berekenen van alle in aanmerking komende koeriers
                    // Hoger dan gemiddelde? --> order is available voor deze koerier
                    return courier.getTotalDeliveryPoints() >= order.getAverageDeliveryPoints(applicableCouriers);
                }
            }
            return false;
        }).toList();
    }

    // Lijst maken van koeriers die in aanmerking komen voor een order
    private List<Courier> getApplicableCouriersForOrder(Order order) {
        // Alle koeriers ophalen en filteren op het volgende
        return courierRepository.getAll().stream().filter(courier -> courier.willArriveInTimeForOrder(order)).toList();
    }

    public void addOrder(Order order) {
        order.setOrderId(orderRepository.getNextAvailableId());
        orderRepository.insert(order.getOrderId(), order);
    }

    public void pickupOrder(int courierId, int orderId, int pickupTimeMinutes) {
        Order order = orderRepository.findById(orderId);
        Courier courier = courierRepository.findById(courierId);
        LocalDateTime timeOrderPickedUp = order.getTimeSelected().plusMinutes(pickupTimeMinutes);

        // State van order aanpassen
        order.setState(OrderState.UNDERWAY_FOR_DELIVERY);

        if(timeOrderPickedUp.isAfter(order.getTimePlaced().plusMinutes(order.getProductionTime() + 10))) {
            System.out.println("Courier picked order up too late");
            EventType eventType = EventType.LATE_PICKUP;
            DeliveryPointEvent deliveryEvent = new DeliveryPointEvent(-20, eventType);
            order.addEvent(new OrderEvent(deliveryEvent, LocalDateTime.now(), eventType, ""));
            courier.addPointEvent(deliveryEvent);
        }else {
            System.out.println("Courier picked order up on time");
            EventType eventType = EventType.TIMELY_PICKUP;
            DeliveryPointEvent deliveryEvent = new DeliveryPointEvent(50, eventType);
            order.addEvent(new OrderEvent(deliveryEvent, LocalDateTime.now(), eventType, ""));
            courier.addPointEvent(deliveryEvent);
        }
    }

    public void deliverOrder(int orderId, int courierId) {

    }
}

