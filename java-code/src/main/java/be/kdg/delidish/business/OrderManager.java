package be.kdg.delidish.business;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.persistence.*;
import be.kdg.delidish.business.domain.person.*;

import java.time.LocalDateTime;
import java.util.List;

public class OrderManager {

	private OrderRepository orderRepository;

    /**
     * @param orderId
     * @param courierId
     */
    public void assignOrder(int orderId, int courierId) {
        Order order = orderRepository.get(orderId);
        Courier courier = courierRepository.get(courierId);

        order.assignCourier(courier);
        order.addEvent(OrderState.ACCEPTED_BY_COURIER);

        orderRepository.update(order);
    }

    public List<Order> getAvailableOrders(long courierId) {
        // Make an empty list to put the available orders in
        List<Order> availableOrders = null;

        // Get all orders
        List<Order> orders = orderRepository.getAll();

        // Loop through all orders and filter the ones that have the last status "order placed"
        orders = orders.stream().filter(order -> order.getLastEvent().getState().equals(OrderState.ORDER_PLACED)).toList();

        for (Order order: orders) {
            // Get datetime from last event
            LocalDateTime lastEventTime = order.getLastEvent().getTime();

            // Is the lastEventTime more than 5 minutes ago?
            if (lastEventTime.isBefore(LocalDateTime.now().minusMinutes(5))) {
                // Add the order to the available orders list
                availableOrders.add(order);
            }
        }

        return availableOrders;
    }
}