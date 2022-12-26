package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.order.OrderLine;
import be.kdg.delidish.business.domain.order.OrderState;
import be.kdg.delidish.business.domain.restaurant.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public class OrderFactory {
    public static Order create(Restaurant restaurant, LocalDateTime timePlaced, OrderState state, List<OrderLine> orderLines, String description) {
        return new Order(restaurant, timePlaced, state, orderLines, description);
    }
}
