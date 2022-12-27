package be.kdg.delidish.business.domain.restaurant;

import java.util.List;

//Composite
public class DishComposite implements Dish {

    private String name;
    private List<Dish> subdishes;

    public void add(Dish dish) {
        subdishes.add(dish);
    }

    @Override
    public int getProductionTime() {
         return subdishes.stream().mapToInt(Dish::getProductionTime).max().orElse(0);
    }

    @Override
    public int getMaximumDeliveryTime() {
        return subdishes.stream().mapToInt(Dish::getMaximumDeliveryTime).max().orElse(0);
    }

    @Override
    public int getMinutesBeforeCold() {
        return subdishes.stream().mapToInt(Dish::getMinutesBeforeCold).min().orElse(0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Restaurant getRestaurant() {
        return subdishes.get(0).getRestaurant();
    }
}
