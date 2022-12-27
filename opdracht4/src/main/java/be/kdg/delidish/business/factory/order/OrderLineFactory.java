package be.kdg.delidish.business.factory.order;

import be.kdg.delidish.business.domain.order.OrderLine;
import be.kdg.delidish.business.domain.restaurant.Dish;

import java.util.List;

public class OrderLineFactory {
    public static OrderLine create(List<Dish> dishIngredients, int quantity) {
        return new OrderLine(dishIngredients, quantity);
    }
}
