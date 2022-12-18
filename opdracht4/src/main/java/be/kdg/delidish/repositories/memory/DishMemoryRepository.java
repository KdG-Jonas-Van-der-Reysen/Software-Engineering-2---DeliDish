package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.business.domain.common.City;
import be.kdg.delidish.business.domain.restaurant.Dish;
import be.kdg.delidish.repositories.CityRepository;
import be.kdg.delidish.repositories.DishRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DishMemoryRepository extends MemoryRepository<Integer, Dish> implements DishRepository {
}

