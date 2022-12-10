package be.kdg.delidish.business;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.persistence.CourierRepository;
import be.kdg.delidish.persistence.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

public enum OrderManager {
    INSTANCE;

	private OrderRepository orderRepository = OrderRepository.INSTANCE;
    private CourierRepository courierRepository = CourierRepository.INSTANCE;

    /**
     * @param orderId
     * @param courierId
     */
    public void assignOrder(int orderId, int courierId) {
        Order order = orderRepository.get(orderId);
        Courier courier = courierRepository.get(courierId);

        if (courier != null && order != null) {
            order.assignCourier(courier);
            order.addEvent(OrderState.ACCEPTED_BY_COURIER);
            orderRepository.update(order);
        }
    }

    public List<Order> getAvailableOrders(int courierId) {
        return orderRepository.getAll()
                .stream()
                .filter(order -> order.getState().equals(OrderState.ORDER_PLACED))
                .filter(order -> order.getLastEvent().getTime().isBefore(LocalDateTime.now().minusMinutes(5)))
                .toList();
    }
}

