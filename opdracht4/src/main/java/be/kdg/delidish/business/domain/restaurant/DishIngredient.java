package be.kdg.delidish.business.domain.restaurant;

import be.kdg.delidish.business.domain.payment.Money;

import java.util.Arrays;

//Node
public class DishIngredient implements Dish {

    private final String name;
    private String description;
    private Money price;
    private final int productionTime;
    private final int maximumDeliveryTime;
    private Allergen[] allergens;
    private boolean orderable;
    private final Restaurant restaurant;


    public DishIngredient(int productionTime, String name, Restaurant restaurant, int maximumDeliveryTime) {
        this.productionTime = productionTime;
        this.name = name;
        this.restaurant = restaurant;
        this.maximumDeliveryTime = maximumDeliveryTime;
    }

    @Override
    public int getProductionTime() {
        return productionTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public int getMaximumDeliveryTime() {
        return maximumDeliveryTime;
    }

    @Override
    public int getMinutesBeforeCold() {
        return productionTime + maximumDeliveryTime;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productionTime=" + productionTime +
                ", maximumDeliveryTime=" + maximumDeliveryTime +
                ", allergens=" + Arrays.toString(allergens) +
                ", orderable=" + orderable +
                ", restaurant=" + restaurant +
                '}';
    }
}