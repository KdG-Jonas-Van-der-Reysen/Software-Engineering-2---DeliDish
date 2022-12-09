package be.kdg.delidish.application;

import be.kdg.delidish.business.*;
import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.person.Courier;

import java.util.List;

public class DeliveryController {

	private OrderManager orderManager;
	private UserManager userManager;

	public DeliveryController(OrderManager orderManager, UserManager userManager) {
		this.orderManager = orderManager;
		this.userManager = userManager;
	}

	/**
	 * 
	 * @param orderId
	 */
	public void selectDelivery(int orderId, int courierId) {
		orderManager.assignOrder(orderId, courierId);
	}

	public List<Order> showAvailableOrders(long courierId) {
		return orderManager.getAvailableOrders(courierId);
	}

}