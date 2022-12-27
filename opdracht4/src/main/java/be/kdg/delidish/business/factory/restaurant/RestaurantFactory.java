package be.kdg.delidish.business.factory.restaurant;

import be.kdg.delidish.business.domain.restaurant.Restaurant;
import be.kdg.delidish.business.domain.common.ContactInfo;
public class RestaurantFactory {
    public static Restaurant create(String name, ContactInfo contactInfo) {
        return new Restaurant(name, contactInfo);
    }
}
