package be.kdg.delidish.business.domain.restaurant;

import be.kdg.delidish.business.domain.payment.Money;

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

    public Dish(int productionTime, String name) {
        this.productionTime = productionTime;
        this.name = name;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public String getName() {
        return name;
    }
}