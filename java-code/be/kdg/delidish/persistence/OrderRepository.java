package be.kdg.delidish.persistence;

import be.kdg.delidish.business.domain.order.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderRepository {
	INSTANCE;

	private Map<Integer, Order> map = new HashMap<>();

	public Order get(int id) {
		
		
	}

	public List<Order> getAll() {
		return (List<Order>) map.values();
	}

	public void update(Order order) {
		
		
	}

}