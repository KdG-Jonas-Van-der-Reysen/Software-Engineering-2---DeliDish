package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.domain.common.Position;
import be.kdg.delidish.business.domain.order.Order;

import java.time.LocalDateTime;
import java.util.*;

public class Courier extends Person {

	private final Partner partner;
	private final List<DeliveryPointEvent> deliveryPointEvents;
	private boolean isAvailable;

	public Courier(String firstName, String lastName, Partner partner, List<DeliveryPointEvent> deliveryPointEvents, boolean isAvailable, Position currentPosition) {
		super(firstName, lastName);
		this.partner = partner;
		this.deliveryPointEvents = deliveryPointEvents;
		this.isAvailable = isAvailable;
		this.currentPosition = currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	private Position currentPosition;

	public void setAvailable(boolean available) {
		this.isAvailable = available;		
	}

	public void addPointEvent(EventType type) {
		DeliveryPointEvent deliveryPointEvent = new DeliveryPointEvent(5,type);//TODO: 5 punten goed?
		deliveryPointEvents.add(deliveryPointEvent);
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public boolean willArriveInTimeForOrder(Order order) {
		// Koerier moet beschikbaar zijn
		if (isAvailable) {
			// Wanneer is de bestelling klaar?
			int orderProductionTime = order.getProductionTime();
			LocalDateTime orderKlaar = order.getTimePlaced().plusMinutes(orderProductionTime);

			// Positie van restaurant ophalen
			Position restaurantPosition = order.getRestaurant().getContactInfo().getAddress().getPosition();

			// Aankomsttijd koerier bij restaurant berekenen
			double distanceInKm = currentPosition.calculateDistance(restaurantPosition);
			int travelTimeInMinutes = (int) (distanceInKm * 4);
			LocalDateTime koerierTerPlaatse = LocalDateTime.now().plusMinutes(travelTimeInMinutes);

			// Koerier moet ter plaatse zijn voordat het order klaar is
			return koerierTerPlaatse.isBefore(orderKlaar);
		} else {
			return false;
		}
	}
	
	public int getTotalDeliveryPoints() {
		return deliveryPointEvents.stream().mapToInt(DeliveryPointEvent::getPoints).sum();
	}
}