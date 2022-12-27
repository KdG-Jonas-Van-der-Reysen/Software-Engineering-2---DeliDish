package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.person.DeliveryPointEvent;
import be.kdg.delidish.business.domain.person.EventType;

import java.time.LocalDateTime;

public class OrderEvent {

	private final DeliveryPointEvent deliveryPointEvent;

	private final LocalDateTime time;
	private final EventType state;
	private final String remark;

	public OrderEvent(DeliveryPointEvent deliveryPointEvent, LocalDateTime time, EventType state, String remark) {
		this.deliveryPointEvent = deliveryPointEvent;
		this.time = time;
		this.state = state;
		this.remark = remark;
	}

	public EventType getState() {
		return state;
	}

	public LocalDateTime getTime() {
		return time;
	}
}