package be.kdg.delidish.business;

import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.EventType;
import be.kdg.delidish.repositories.memory.CourierMemoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserManager {
	private final CourierMemoryRepository courierRepository;

    UserManager(CourierMemoryRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public void orderAccepted(Courier courier, LocalDateTime acceptTime) {
        courier.setAvailable(true);
        courier.addPointEvent(new DeliveryPointEvent(50, EventType.ORDER_ACCEPTED));
        courierRepository.update(courier.getPersonId(), courier);
    }

	public Courier getCourier(int id) {
		return courierRepository.findById(id);
	}

	public void addCourier(Courier courier) {
        courier.setPersonId(courierRepository.getNextAvailableId());
        courierRepository.insert(courier.getPersonId() ,courier);
	}
}