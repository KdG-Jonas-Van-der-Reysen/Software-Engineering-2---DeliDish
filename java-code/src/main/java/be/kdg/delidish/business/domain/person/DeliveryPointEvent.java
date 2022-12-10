package be.kdg.delidish.business.domain.person;

import java.time.LocalDateTime;

public class DeliveryPointEvent {

	private LocalDateTime time;
	private int points;
	private int eventType;

	/**
	 * 
	 * @param points
	 * @param type
	 */
	public DeliveryPointEvent(int points, EventType type) {
		
		
	}

	public int getPoints() {
		return points;
	}
}