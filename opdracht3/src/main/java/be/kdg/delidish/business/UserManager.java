package be.kdg.delidish.business;

import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.EventType;
import be.kdg.delidish.persistence.CourierRepository;

public enum UserManager {
    INSTANCE;

	private CourierRepository courierRepository  = CourierRepository.INSTANCE;

    public void orderAccepted(Courier courier) {
        courier.setAvailable(true);
        courier.addPointEvent(EventType.ORDER_ACCEPTED);
        courierRepository.update(courier);
    }

	public Courier getCourier(int id) {
		return courierRepository.get(id);
	}

	public void addCourier(Courier courier) {
        courierRepository.add(courier);
	}
}