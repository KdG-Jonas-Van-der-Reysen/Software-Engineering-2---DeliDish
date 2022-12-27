package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.restaurant.Dish;
import be.kdg.delidish.business.domain.restaurant.DishComposite;
import be.kdg.delidish.business.domain.restaurant.DishIngredient;
import be.kdg.delidish.business.domain.restaurant.Restaurant;

import java.util.List;

public class DishFactory {
    public static DishIngredient createIngredient(int productionTime, String name, Restaurant restaurant, int maximumDeliveryTime) {
        return new DishIngredient(productionTime, name, restaurant, maximumDeliveryTime);
    }

    public static DishComposite createComposite(String name, List<Dish> subdishes) {
        return new DishComposite(name, subdishes);
    }
}
