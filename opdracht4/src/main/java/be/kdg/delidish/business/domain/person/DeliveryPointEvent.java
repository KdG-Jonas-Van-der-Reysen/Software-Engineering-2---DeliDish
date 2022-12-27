package be.kdg.delidish.business.domain.person;

import java.util.Objects;

public class DeliveryPointEvent {

	private final EventType eventType;

	public DeliveryPointEvent(EventType type) {
		this.eventType = type;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		DeliveryPointEvent deliveryPointEvent = (DeliveryPointEvent) o;
		return deliveryPointEvent.getPoints() == eventType.points && eventType == deliveryPointEvent.getEventType();
	}


	@Override
	public int hashCode() {
		return Objects.hash(getPoints(), getEventType());
	}

	public int getPoints() {
		return eventType.points;
	}

	public EventType getEventType() {
		return eventType;
	}
}