package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.person.*;

public class OrderEvent {

	private DeliveryPointEvent deliveryPointEvent;
	private LocalDateTime time;
	private EventType state;
	private String remark;

}