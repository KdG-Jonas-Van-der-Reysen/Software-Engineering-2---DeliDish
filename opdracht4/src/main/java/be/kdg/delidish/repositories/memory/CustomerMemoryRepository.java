package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.business.domain.person.Customer;
import be.kdg.delidish.repositories.CustomerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerMemoryRepository extends MemoryRepository<Customer> implements CustomerRepository {
}

