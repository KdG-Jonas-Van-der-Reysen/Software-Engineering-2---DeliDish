package be.kdg.delidish.business;

import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.repositories.memory.CourierMemoryRepository;
import org.springframework.stereotype.Service;

@Service
public class UserManager {
	private final CourierMemoryRepository courierRepository;

    UserManager(CourierMemoryRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public void orderAccepted(int courierId) {
        Courier courier = courierRepository.findById(courierId);
        courier.setAvailable(false);
        courierRepository.update(courierId, courier);
    }

	public Courier getCourier(int id) {
		return courierRepository.findById(id);
	}

	public void addCourier(Courier courier) {
        courierRepository.insert(courierRepository.getNextAvailableId(), courier);
	}
}