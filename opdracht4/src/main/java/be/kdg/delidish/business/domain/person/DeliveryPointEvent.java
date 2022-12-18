package be.kdg.delidish.business.domain.person;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeliveryPointEvent {

	private int points;
	private EventType eventType;

	public DeliveryPointEvent(int points, EventType type) {
		this.points = points;
		this.eventType = type;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass());
		DeliveryPointEvent dpe = (DeliveryPointEvent) o;
		return points == dpe.getPoints() && eventType == dpe.getEventType();
	}


	@Override
	public int hashCode() {
		return Objects.hash(getPoints(), getEventType());
	}

	public int getPoints() {
		return points;
	}

	public EventType getEventType() {
		return eventType;
	}
}