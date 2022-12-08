package be.kdg.delidish.business.domain.order;


import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.Customer;

import java.util.List;

public class Order {

	private List<OrderlLine> orderLines;
	private List<OrderEvent> orderEvents;
	Customer customer;
	Courier courier;
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

	}

	/**
	 * 
	 * @param os
	 */
	public void addEvent(OrderState os) {

	}

}