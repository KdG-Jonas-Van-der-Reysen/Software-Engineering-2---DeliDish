package be.kdg.delidish.application;

import be.kdg.delidish.business.OrderManager;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private OrderManager orderManager;

    public OrderController(OrderManager orderManger) {
        this.orderManager = orderManger;
    }

    public void pickupOrder (int courierId, int orderId, int minutesDelayed){
        orderManager.pickupOrder(courierId, orderId, minutesDelayed);
    }
}
