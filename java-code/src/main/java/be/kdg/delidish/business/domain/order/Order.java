package be.kdg.delidish.business.domain.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import be.kdg.delidish.business.domain.person.*;
import be.kdg.delidish.business.domain.common.*;
import be.kdg.delidish.business.domain.restaurant.Restaurant;

public class Order {

	private List<OrderLine> orderLines;
	private List<OrderEvent> orderEvents;
	private Customer customer;
	private Courier courier;

	private int orderId;

	private Address deliveryAddress;
	private String deliveryInstructions;
	private int averageDeliveryPoints;
	private LocalDateTime placedAt;
	private OrderState state;
	private Restaurant restaurant;

	/**
	 * 
	 * @param courier
	 */
	public void assignCourier(Courier courier) {
		this.courier = courier;
	}

	public void addEvent(OrderState os) {
		
		
	}

	public OrderState getState() {
		return state;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCourierId() {
		return courier.getPersonId();
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public LocalDateTime getPlacedAt() {
		return placedAt;
	}

	public int getProductionTime() {
		// Get order line with the highest production time
		Optional<OrderLine> maxProductionTime = orderLines.stream()
				.max((o1, o2) -> o1.getProductionTime() - o2.getProductionTime());

		if(maxProductionTime.isPresent()) {
			return maxProductionTime.get().getProductionTime();
		} else {
			// Gaat nooit hierin komen maar kijk, IntelliJ klaagt anders
			return 0;
		}
	}
}