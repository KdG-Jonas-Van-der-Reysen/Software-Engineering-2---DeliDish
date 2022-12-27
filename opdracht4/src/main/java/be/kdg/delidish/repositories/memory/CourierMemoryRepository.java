package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.repositories.CourierRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CourierMemoryRepository extends MemoryRepository<Courier> implements CourierRepository {
}

