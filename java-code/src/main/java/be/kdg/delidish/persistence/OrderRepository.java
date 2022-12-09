package be.kdg.delidish.persistence;

import be.kdg.delidish.business.domain.order.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderRepository {
	INSTANCE;

	private Map<Integer, Order> map = new HashMap<>();

	public Order get(int id) {
		//TODO implement this method before using!
		return null;
	}

	public List<Order> getAll() {
		return (List<Order>) map.values();
	}

	public void update(Order order) {
		
		
	}

}