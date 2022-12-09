package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.restaurant.Dish;

import java.util.List;

public class OrderlLine {

	private List<Dish> dishes;
	private int quantity;
	private String remark;

	public List<Dish> getDishes() {
		return dishes;
	}
}