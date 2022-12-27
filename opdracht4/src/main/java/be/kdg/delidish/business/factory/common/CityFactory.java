package be.kdg.delidish.business.factory.common;

import be.kdg.delidish.business.domain.common.City;

public class CityFactory {
    public static City create(int postal, String name, String country) {
        return new City(postal, name, country);
    }
}
