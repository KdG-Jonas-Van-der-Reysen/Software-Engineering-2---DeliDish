package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.restaurant.DishIngredient;

import java.util.List;
import java.util.stream.Collectors;

public class OrderLine {

	private List<DishIngredient> dishIngredients;	//TODO: list van composites
	private int quantity;
	private String remark;

	public OrderLine(List<DishIngredient> dishIngredients, int quantity) {
		this.dishIngredients = dishIngredients;
		this.quantity = quantity;
	}

	public List<DishIngredient> getDishes() {
		return dishIngredients;
	}

	public int getProductionTime() {
		return dishIngredients.stream().mapToInt(DishIngredient::getProductionTime).max().orElse(0);
	}

	public int getMaximumDeliveryTime() {
		return dishIngredients.stream().mapToInt(DishIngredient::getMaximumDeliveryTime).min().orElse(0);
	}

	public int getMinutesBeforeCold() {
		return dishIngredients.stream().mapToInt(DishIngredient::getMinutesBeforeCold).min().orElse(0);
	}

	public String toString() {
		 return dishIngredients.stream().map(DishIngredient::getName).collect(Collectors.joining( "," ));
	}
}