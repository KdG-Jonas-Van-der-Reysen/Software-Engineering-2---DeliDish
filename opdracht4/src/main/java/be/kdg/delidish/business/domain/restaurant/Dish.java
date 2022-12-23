package be.kdg.delidish.business.domain.restaurant;

import be.kdg.delidish.business.domain.payment.Money;
import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;

import java.util.Arrays;
import java.util.List;

public class Dish {

    private List<Dish> subdishes;
    private String name;
    private String description;
    private Money price;
    private int productionTime;
    private int maximumDeliveryTime;
    private Allergen[] allergens;
    private boolean orderable;
    private Restaurant restaurant;

    public Dish(int productionTime, String name, Restaurant restaurant, int maximumDeliveryTime) {
        this.productionTime = productionTime;
        this.name = name;
        this.restaurant = restaurant;
        this.maximumDeliveryTime = maximumDeliveryTime;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public String getName() {
        return name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getMaximumDeliveryTime() {
        return maximumDeliveryTime;
    }

    public int getMinutesBeforeCold() {
        System.out.println(productionTime + " " + maximumDeliveryTime);
        return productionTime + maximumDeliveryTime;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "subdishes=" + subdishes +
                ", name='" + name + '\'' +
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