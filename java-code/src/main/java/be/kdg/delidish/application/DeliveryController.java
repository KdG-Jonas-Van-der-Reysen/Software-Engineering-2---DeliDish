package be.kdg.delidish.application;

import be.kdg.delidish.business.OrderManager;
import be.kdg.delidish.business.UserManager;
import be.kdg.delidish.business.domain.order.Order;

import java.util.List;

public class DeliveryController {
	private static DeliveryController instance;

	private OrderManager orderManager;
	private UserManager userManager;

	private DeliveryController() {
		orderManager = OrderManager.INSTANCE;
		userManager = UserManager.INSTANCE;
	}

	public static DeliveryController getInstance() {
		if(instance == null) { // lazy initialization
			instance = new DeliveryController();
		}
		return instance;
	}

	/**
	 * 
	 * @param orderId TODO JONAS 1 parameter here / 2 parameter daar?
	 */
	public void selectDelivery(int orderId, int courierId) {
		orderManager.assignOrder(orderId, courierId);
	}

	public List<Order> showAvailableOrders(int courierId) {
		return orderManager.getAvailableOrders(courierId);
	}

}