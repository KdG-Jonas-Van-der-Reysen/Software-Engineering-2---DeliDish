package be.kdg.delidish.application;

import be.kdg.delidish.business.OrderManager;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {
    private final OrderManager orderManager;

    public OrderController(OrderManager orderManger) {
        this.orderManager = orderManger;
    }

    public void pickupOrder (int courierId, int orderId, int minutesDelayed){
        orderManager.pickupOrder(courierId, orderId, minutesDelayed);
    }

    public void addOrderWithDishForCustomer(String description, int dishId, int minutesAgo, String orderState, int customerId) {
        orderManager.addOrderWithDishForCustomer(description, dishId, minutesAgo,orderState,customerId);
    }

    public void addOrderWithDishesForCustomer(String description, List<Integer> dishes, int minutesAgo, String orderState, int customerId) {
        orderManager.addOrderWithDishesForCustomer(description, dishes, minutesAgo, orderState, customerId);
    }

    public void pickupOrderAfterMinutes(int minutes, int orderId) {
        orderManager.pickupOrderAfterMinutes(minutes, orderId);
    }
}
