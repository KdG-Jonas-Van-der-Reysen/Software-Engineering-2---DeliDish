package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.restaurant.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderLine {

	private final List<Dish> dishes;
	private final int quantity;
	private String remark;

	public OrderLine(List<Dish> dishes, int quantity) {
		this.dishes = dishes;
		this.quantity = quantity;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public int getProductionTime() {
		Optional<Dish> productionTime = dishes.stream().max(Comparator.comparingInt(Dish::getProductionTime));

		// Gaat nooit hierin komen maar kijk, IntelliJ klaagt anders
		return productionTime.map(Dish::getProductionTime).orElse(0);
	}

	public String toString() {
		 return dishes.stream().map(Dish::getName).collect(Collectors.joining( "," ));
	}
}