package be.kdg.delidish.business.strategy.availableOrders;

import be.kdg.delidish.business.domain.order.Order;

import java.util.List;

public interface AvailableOrdersStrategy {
    List<Order> getAvailableOrders(int courierId);
}
