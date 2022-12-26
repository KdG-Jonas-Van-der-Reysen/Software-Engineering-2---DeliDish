package be.kdg.delidish.business;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderEvent;
import be.kdg.delidish.business.domain.order.OrderLine;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.Customer;
import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.EventType;
import be.kdg.delidish.business.domain.restaurant.DishIngredient;
import be.kdg.delidish.business.domain.restaurant.Restaurant;
import be.kdg.delidish.business.factory.DeliveryPointEventFactory;
import be.kdg.delidish.business.factory.OrderEventFactory;
import be.kdg.delidish.business.factory.OrderFactory;
import be.kdg.delidish.business.factory.OrderLineFactory;
import be.kdg.delidish.business.strategy.availableOrders.AvailableOrdersStrategy;
import be.kdg.delidish.repositories.memory.CourierMemoryRepository;
import be.kdg.delidish.repositories.memory.CustomerMemoryRepository;
import be.kdg.delidish.repositories.memory.DishMemoryRepository;
import be.kdg.delidish.repositories.memory.OrderMemoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderManager {

    private final OrderMemoryRepository orderRepository;
    private final CourierMemoryRepository courierRepository;
    private final DishMemoryRepository dishRepository;
    private final CustomerMemoryRepository customerRepository;

    // Strategies
    private final AvailableOrdersStrategy availableOrdersStrategy;


    OrderManager(OrderMemoryRepository orderRepository, CourierMemoryRepository courierRepository, DishMemoryRepository dishRepository, CustomerMemoryRepository customerRepository, @Qualifier("MustArriveInTime") AvailableOrdersStrategy availableOrdersStrategy) {
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
        this.dishRepository = dishRepository;
        this.customerRepository = customerRepository;
        this.availableOrdersStrategy = availableOrdersStrategy;
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
        return availableOrdersStrategy.getAvailableOrders(courierId);
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
            EventType eventType = EventType.LATE_PICKUP;
            DeliveryPointEvent deliveryEvent = new DeliveryPointEvent(-20, eventType);
            order.addEvent(new OrderEvent(deliveryEvent, LocalDateTime.now(), eventType, ""));
            courier.addPointEvent(deliveryEvent);
        }else {
            EventType eventType = EventType.TIMELY_PICKUP;
            DeliveryPointEvent deliveryEvent = new DeliveryPointEvent(50, eventType);
            order.addEvent(new OrderEvent(deliveryEvent, LocalDateTime.now(), eventType, ""));
            courier.addPointEvent(deliveryEvent);
        }
    }

    public void deliverOrder(int orderId, int courierId) {

    }

    public void addOrderWithDishForCustomer(String description, int dishId, int minutesAgo, String orderState, int customerId) {
        List<Integer> dishes = new ArrayList<>();
        dishes.add(dishId);

        this.addOrderWithDishesForCustomer(description, dishes, minutesAgo, orderState, customerId);
    }

    public void addOrderWithDishesForCustomer(String description, List<Integer> dishesIds, int minutesAgo, String orderState, int customerId) {
        List<OrderLine> orderLines = new ArrayList<>();

        for (Integer dishId : dishesIds) {
            // Find the dish
            DishIngredient dishIngredient = dishRepository.findById(dishId);

            // Create the order line
            List<DishIngredient> dishesInOrderLine = new ArrayList<>();
            dishesInOrderLine.add(dishIngredient);
            OrderLine orderLine = OrderLineFactory.create(dishesInOrderLine, 1);

            orderLines.add(orderLine);
        }

        // Get the restaurant via the dish
        Restaurant restaurant = orderLines.get(0).getDishes().get(0).getRestaurant();

        // Get the order state
        OrderState state = OrderState.valueOf(orderState);

        // Create the order
        Order order = OrderFactory.create(restaurant, LocalDateTime.now().minusMinutes(minutesAgo), state, orderLines, description);
        order.setOrderId(orderRepository.getNextAvailableId());

        // Get the customer
        Customer cmr = customerRepository.findById(customerId);

        // Assign the customer
        order.assignCustomer(cmr);

        // Add order to repository
        orderRepository.insert(order.getOrderId(), order);
    }

    public void pickupOrderAfterMinutes(int minutes, int orderId) {
        Order order = orderRepository.findById(orderId);
        Courier courier = courierRepository.findById(order.getCourierId());

        // Delivery points toevoegen
        /// Calculate when the order has actually been delivered
        LocalDateTime orderActuallyDelivered = order.getTimePlaced().plusMinutes(minutes);

        DeliveryPointEvent dpe;

        if (orderActuallyDelivered.isBefore(order.getOrderIsColdAt())) {
            //// Order has been delivered on time
            dpe = DeliveryPointEventFactory.create(50, EventType.TIMELY_DELIVERY);
        }else {
            //// Order has been delivered too late
            dpe = DeliveryPointEventFactory.create(-20, EventType.LATE_DELIVERY);
        }

        courier.addPointEvent(dpe);

        // Order aanpassen //
        /// Update order state
        order.setState(OrderState.DELIVERED);

        /// Add an event to the order, so we know when it's been delivered
        OrderEvent oe = OrderEventFactory.create(
                dpe,
                orderActuallyDelivered,
                dpe.getEventType(),
                "Order has been delivered! Enjoy your meal."
        );

        order.addEvent(oe);

        //Update
        orderRepository.update(order.getOrderId(), order);
    }
}
