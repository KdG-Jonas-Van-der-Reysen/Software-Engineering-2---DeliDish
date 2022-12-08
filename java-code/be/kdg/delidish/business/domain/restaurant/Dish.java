package be.kdg.delidish.business.domain.restaurant;

import java.util.*;
import be.kdg.delidish.business.domain.payment.*;

public class Dish {

	private List<Dish> subdishes;
	private String name;
	private String description;
	private Money price;
	private int productionTime;
	private int maximumDeliveryTime;
	private Allergen[] allergens;
	private boolean orderable;

}