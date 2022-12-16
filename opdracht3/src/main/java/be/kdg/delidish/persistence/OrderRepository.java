package be.kdg.delidish.persistence;

import be.kdg.delidish.business.domain.order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderRepository {
	INSTANCE;

	private Map<Integer, Order> map;

	OrderRepository() {
		map = new HashMap<>();
	}

	public Order get(int id) {
		return map.get(id);
	}

	public List<Order> getAll() {
		return new ArrayList<>(map.values());
	}

	public Order add(Order order) {
		order.setOrderId(map.size());
		map.put(order.getOrderId(), order);

		return order;
	}

	public void update(Order order) {
		map.replace(order.getOrderId(), order);
	}

}