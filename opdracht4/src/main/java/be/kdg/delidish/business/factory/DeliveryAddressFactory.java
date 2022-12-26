package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.common.City;
import be.kdg.delidish.business.domain.common.DeliveryAddress;
import be.kdg.delidish.business.domain.common.Position;
import be.kdg.delidish.business.domain.person.Customer;

public class DeliveryAddressFactory {
    public static DeliveryAddress create(City city, String street, String number, Position position, String country, Customer customer) {
        return new DeliveryAddress(city, street, number, position, country, customer);
    }
}

