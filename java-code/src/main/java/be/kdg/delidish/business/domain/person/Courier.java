package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.domain.common.Position;

import java.util.List;

public class Courier extends Person {

	private Partner partner;
	private List<DeliveryPointEvent> deliveryPointEvents;
	private boolean isAvailable;
	private Position currentPosition;

	/**
	 * 
	 * @param available
	 */
	public void setAvailable(boolean available) {
		
		
	}

	/**
	 * 
	 * @param type
	 */
	public void addPointEvent(EventType type) {
		
		
	}

}