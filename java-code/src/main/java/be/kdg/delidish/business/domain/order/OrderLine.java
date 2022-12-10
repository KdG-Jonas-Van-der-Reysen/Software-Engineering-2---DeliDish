package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.restaurant.Dish;

import java.util.List;
import java.util.Optional;

public class OrderLine {

	private List<Dish> dishes;
	private int quantity;
	private String remark;

	public List<Dish> getDishes() {
		return dishes;
	}

	public int getProductionTime() {
		Optional<Dish> productionTime = dishes.stream()
				.max((d1, d2) -> d1.getProductionTime() - d2.getProductionTime());

		if(productionTime.isPresent()) {
			return productionTime.get().getProductionTime();
		} else {
			// Gaat nooit hierin komen maar kijk, IntelliJ klaagt anders
			return 0;
		}
	}
}