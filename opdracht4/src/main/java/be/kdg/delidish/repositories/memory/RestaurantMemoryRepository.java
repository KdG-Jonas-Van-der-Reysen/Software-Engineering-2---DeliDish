package be.kdg.delidish.repositories.memory;
import be.kdg.delidish.business.domain.restaurant.Restaurant;
import be.kdg.delidish.repositories.RestaurantRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantMemoryRepository extends MemoryRepository<Restaurant> implements RestaurantRepository {
}

