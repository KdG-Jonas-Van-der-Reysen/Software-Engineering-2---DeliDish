package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.adapter.DistanceCalculator;
import be.kdg.delidish.business.domain.common.Position;
import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.factory.person.DeliveryPointEventFactory;

import java.time.LocalDateTime;
import java.util.List;

public class Courier extends Person {

	private Partner partner;
	private final List<DeliveryPointEvent> deliveryPointEvents;
	private boolean isAvailable;
	private final DistanceCalculator distanceCalculator;

	public Courier(String firstName, String lastName, List<DeliveryPointEvent> deliveryPointEvents, boolean isAvailable, Position currentPosition, DistanceCalculator distanceCalculator) {
		super(firstName, lastName);
		this.deliveryPointEvents = deliveryPointEvents;
		this.isAvailable = isAvailable;
		this.currentPosition = currentPosition;
		this.distanceCalculator = distanceCalculator;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	private Position currentPosition;

	public void setAvailable(boolean available) {
		this.isAvailable = available;		
	}

	public void addPointEvent(DeliveryPointEvent deliveryPointEvent) {
		deliveryPointEvents.add(deliveryPointEvent);
	}

	public void addPointEvent(EventType type) {
		DeliveryPointEvent deliveryPointEvent = DeliveryPointEventFactory.create(type);
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
			LocalDateTime orderKlaar = order.getTimePlaced().plusMinutes(order.getProductionTime());

			// Positie van restaurant ophalen
			Position restaurantPosition = order.getRestaurant().getContactInfo().getAddress().getPosition();

			// Aankomsttijd koerier bij restaurant berekenen
			double distanceInKm = distanceCalculator.getDistance(currentPosition, restaurantPosition);
			int travelTimeInMinutes = (int) (distanceInKm * 4);
			LocalDateTime koerierTerPlaatse = LocalDateTime.now().plusMinutes(travelTimeInMinutes);

			// Koerier moet ter plaatse zijn voordat het order klaar is
			return koerierTerPlaatse.isBefore(orderKlaar);
		} else {
			return false;
		}
	}

	public List<DeliveryPointEvent> getDeliveryPointEvents() {
		return deliveryPointEvents;
	}
	
	public int getTotalDeliveryPoints() {
		return deliveryPointEvents.stream().mapToInt(DeliveryPointEvent::getPoints).sum();
	}
}