package be.kdg.delidish.business.strategy.availableOrders;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.repositories.memory.OrderMemoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("AllOrders")
public class AllOrders implements AvailableOrdersStrategy {
    private OrderMemoryRepository orderRepository;

    public AllOrders(OrderMemoryRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAvailableOrders(int courierId) {
        return orderRepository.getAll();
    }
}
