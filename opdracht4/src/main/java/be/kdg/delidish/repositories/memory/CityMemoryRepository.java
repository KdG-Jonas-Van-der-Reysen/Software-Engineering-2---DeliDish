package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.business.domain.common.City;
import be.kdg.delidish.repositories.CityRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CityMemoryRepository extends MemoryRepository<Integer, City> implements CityRepository {
}

