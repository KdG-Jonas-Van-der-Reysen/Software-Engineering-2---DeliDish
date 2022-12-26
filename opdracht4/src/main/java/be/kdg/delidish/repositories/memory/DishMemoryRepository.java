package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.business.domain.restaurant.DishIngredient;
import be.kdg.delidish.repositories.DishRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DishMemoryRepository extends MemoryRepository<Integer, DishIngredient> implements DishRepository {
}

