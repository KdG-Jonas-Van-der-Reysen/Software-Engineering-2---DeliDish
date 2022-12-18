package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.repositories.CourierRepository;
import be.kdg.delidish.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderMemoryRepository extends MemoryRepository<Integer, Order> implements OrderRepository {

}

