package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.Customer;
import be.kdg.delidish.business.domain.restaurant.Restaurant;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Order {

	private int orderId;
	private List<OrderLine> orderLines;
	private List<OrderEvent> orderEvents;
	private Customer customer;
	private Courier courier;
	private Address deliveryAddress;
	private String deliveryInstructions;
	private LocalDateTime timePlaced;
	private OrderState state;
	private Restaurant restaurant;

	public Order(Restaurant restaurant, LocalDateTime timePlaced, OrderState state, List<OrderLine> orderLines) {
		this.restaurant = restaurant;
		this.timePlaced = timePlaced;
		this.state = state;
		this.orderLines = orderLines;
	}

	public void assignCourier(Courier courier) {
		this.courier = courier;
	}

	public void addEvent(OrderState os) {
		state = os;
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

	public int getProductionTime() {
		// Get order line with the highest production time
		Optional<OrderLine> maxProductionTime = orderLines.stream().max(Comparator.comparingInt(OrderLine::getProductionTime));

		// Gaat nooit hierin komen maar kijk, IntelliJ klaagt anders
		return maxProductionTime.map(OrderLine::getProductionTime).orElse(0);
	}

	public int getAverageDeliveryPoints(List<Courier> applicableCouriers) {
		// Eerst alle applicable koeriers ophalen -> gemiddelde berekenen
		return (int) applicableCouriers.stream().mapToDouble(Courier::getTotalDeliveryPoints).average().getAsDouble();

		// return averageDeliveryPoints;
	}

	public LocalDateTime getTimePlaced() {
		return timePlaced;
	}

	@Override
	public String toString() {
		return " - [" + orderId + "] Order met gerechten " + orderLines;
	}
}