package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.domain.common.Position;
import be.kdg.delidish.business.domain.order.Order;

import java.time.LocalDateTime;
import java.util.*;

public class Courier extends Person {

	private Partner partner;
	private List<DeliveryPointEvent> deliveryPointEvents;
	private boolean isAvailable;



	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	private Position currentPosition;

	public Courier(Partner partner, List<DeliveryPointEvent> deliveryPointEvents, boolean isAvailable, Position currentPosition) {
		this.partner = partner;
		this.deliveryPointEvents = deliveryPointEvents;
		this.isAvailable = isAvailable;
		this.currentPosition = currentPosition;
	}

	/**
	 * 
	 * @param available
	 */
	public void setAvailable(boolean available) {
		
		
	}

	/**
	 * 
	 * @param type
	 */
	public void addPointEvent(EventType type) {
		
		
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
			LocalDateTime orderKlaar = order.getPlacedAt().plusMinutes(orderProductionTime);

			// Positie van restaurant ophalen
			Position restaurantPosition = order.getRestaurant().getContactInfo().getAddress().getPosition();

			// Aankomsttijd koerier bij restaurant berekenen
			double distanceInKm = currentPosition.calculateDistance(restaurantPosition);
			int travelTimeInMinutes = (int) (distanceInKm * 4);
			LocalDateTime koerierTerPlaatse = LocalDateTime.now().plusMinutes(travelTimeInMinutes);

			// Koerier moet ter plaatse zijn voordat het order klaar is
			if (koerierTerPlaatse.isBefore(orderKlaar)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean isAboveAverageDeliverer(){
		return false;//TODO
	}
}