package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.order.OrderLine;
import be.kdg.delidish.business.domain.restaurant.DishIngredient;

import java.util.List;

public class OrderLineFactory {
    public static OrderLine create(List<DishIngredient> dishIngredients, int quantity) {
        return new OrderLine(dishIngredients, quantity);
    }
}
