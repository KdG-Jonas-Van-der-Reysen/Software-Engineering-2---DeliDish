package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.domain.common.Position;

import java.util.*;

public class Courier extends Person {

	private Partner partner;
	private List<DeliveryPointEvent> deliveryPointEvents;
	private boolean isAvailable;
	private Position currentPosition;

	public Courier(Partner partner, List<DeliveryPointEvent> deliveryPointEvents, boolean isAvailable, Position currentPosition) {
		this.partner = partner;
		this.deliveryPointEvents = deliveryPointEvents;
		this.isAvailable = isAvailable;
		this.currentPosition = currentPosition;
	}

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