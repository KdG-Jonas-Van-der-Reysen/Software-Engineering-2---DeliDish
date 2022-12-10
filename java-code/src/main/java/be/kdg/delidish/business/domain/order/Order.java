package be.kdg.delidish.business.domain.order;

import java.util.*;
import be.kdg.delidish.business.domain.person.*;
import be.kdg.delidish.business.domain.common.*;

public class Order {

	private List<OrderlLine> orderLines;
	private List<OrderEvent> orderEvents;
	private Customer customer;
	private Courier courier;

	private int orderId;
	private Address deliveryAddress;
	private String deliveryInstructions;
	private int averageDeliveryPoints;
	private OrderState state;

	/**
	 * 
	 * @param courier
	 */
	public void assignCourier(Courier courier) {
		this.courier = courier;
	}

	/**
	 * 
	 * @param os
	 */
	public void addEvent(OrderState os) {
		
		
	}

	public OrderEvent getLastEvent() {
		// Return the last event from the orderEvents list TODO: JONAS zelf gemaakt? Nodig?
		return orderEvents.get(orderEvents.size() - 1);
	}

	public OrderState getState() {
		return state;
	}

	/**
	 *
	 * @return orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



}