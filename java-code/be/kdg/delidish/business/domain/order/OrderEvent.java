package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.person.*;

import java.time.LocalDateTime;

public class OrderEvent {

	private DeliveryPointEvent deliveryPointEvent;

	private LocalDateTime time;
	private EventType state;
	private String remark;

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