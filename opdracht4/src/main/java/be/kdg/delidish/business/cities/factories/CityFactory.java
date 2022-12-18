package be.kdg.delidish.business.cities.factories;

import be.kdg.delidish.business.domain.common.City;
import org.springframework.stereotype.Component;

@Component
public class CityFactory {
    public City createCity(int postal, String name, String country) {
        return new City(postal, name, country);
    }
}
