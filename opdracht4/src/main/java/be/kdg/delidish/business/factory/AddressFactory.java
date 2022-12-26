package be.kdg.delidish.business.factory;

import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.domain.common.City;
import be.kdg.delidish.business.domain.common.Position;

public class AddressFactory {
    public static Address create(City city, String street, String number, Position position, String country) {
        return new Address(city, street, number, position, country);
    }
}
