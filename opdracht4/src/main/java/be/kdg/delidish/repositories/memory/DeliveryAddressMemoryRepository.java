package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.business.domain.common.DeliveryAddress;
import be.kdg.delidish.repositories.DeliveryAddressRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryAddressMemoryRepository extends MemoryRepository<DeliveryAddress> implements DeliveryAddressRepository {
}

