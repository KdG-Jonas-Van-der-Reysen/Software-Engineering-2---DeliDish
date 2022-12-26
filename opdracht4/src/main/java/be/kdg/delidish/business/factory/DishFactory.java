package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.restaurant.DishIngredient;
import be.kdg.delidish.business.domain.restaurant.Restaurant;

public class DishFactory {
    public static DishIngredient create(int productionTime, String name, Restaurant restaurant, int maximumDeliveryTime) {
        return new DishIngredient(productionTime, name, restaurant, maximumDeliveryTime);
    }
}
