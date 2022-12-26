package be.kdg.delidish.business.domain.restaurant;

import java.util.List;

//Composite
public class DishComposite implements Dish {

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
}
