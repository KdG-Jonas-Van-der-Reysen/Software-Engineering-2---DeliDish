package be.kdg.delidish.business.domain.person;

import java.time.LocalDateTime;

public class DeliveryPointEvent {

	private LocalDateTime time;
	private int points;
	private EventType eventType;

	public DeliveryPointEvent(int points, EventType type) {
		this.points = points;
		this.eventType = type;
	}

	public int getPoints() {
		return points;
	}
}