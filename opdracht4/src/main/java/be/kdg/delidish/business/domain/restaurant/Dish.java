package be.kdg.delidish.business.domain.restaurant;

// Component
public interface Dish {
    String getName();
    Restaurant getRestaurant();
    int getProductionTime();
    int getMaximumDeliveryTime();
    int getMinutesBeforeCold();
}
