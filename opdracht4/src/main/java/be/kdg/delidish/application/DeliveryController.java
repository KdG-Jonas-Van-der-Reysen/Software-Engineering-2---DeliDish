package be.kdg.delidish.application;

import be.kdg.delidish.business.OrderManager;
import be.kdg.delidish.business.UserManager;
import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.person.Courier;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DeliveryController {

    private final OrderManager orderManager;
    private final UserManager userManager;

    public DeliveryController(OrderManager orderManger, UserManager userManager) {
        this.orderManager = orderManger;
        this.userManager = userManager;
    }

    public void selectDelivery(int orderId, int courierId, LocalDateTime ldt) {
        orderManager.assignOrder(orderId, courierId, ldt);
        // TODO: Stukje weghalen want dit wordt met publisher / subscriber
        userManager.orderAccepted(courierId);
    }

    public List<Order> showAvailableOrders(int courierId) {
        return orderManager.getAvailableOrders(courierId);
    }

    public void addCourier(Courier courier) {
        userManager.addCourier(courier);
    }

    public void addOrder(Order order) {
        orderManager.addOrder(order);
    }

    public Courier getCourier(int id) {
        return userManager.getCourier(id);
    }
}