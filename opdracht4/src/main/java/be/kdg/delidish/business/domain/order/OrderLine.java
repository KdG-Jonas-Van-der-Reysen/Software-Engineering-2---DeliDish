package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.restaurant.Dish;

import java.util.List;
import java.util.stream.Collectors;

public class OrderLine {

	private final List<Dish> dishIngredients;
	private int quantity;
	private String remark;

	public OrderLine(List<Dish> dishIngredients, int quantity) {
		this.dishIngredients = dishIngredients;
		this.quantity = quantity;
	}

	public List<Dish> getDishes() {
		return dishIngredients;
	}

	public int getProductionTime() {
		return dishIngredients.stream().mapToInt(Dish::getProductionTime).max().orElse(0);
	}

	public int getMaximumDeliveryTime() {
		return dishIngredients.stream().mapToInt(Dish::getMaximumDeliveryTime).min().orElse(0);
	}

	public int getMinutesBeforeCold() {
		return dishIngredients.stream().mapToInt(Dish::getMinutesBeforeCold).min().orElse(0);
	}

	public String toString() {
		 return dishIngredients.stream().map(Dish::getName).collect(Collectors.joining( "," ));
	}
}