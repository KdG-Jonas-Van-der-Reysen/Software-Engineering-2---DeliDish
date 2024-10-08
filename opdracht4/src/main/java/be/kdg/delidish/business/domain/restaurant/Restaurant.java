package be.kdg.delidish.business.domain.restaurant;

import be.kdg.delidish.business.domain.common.ContactInfo;

import java.util.List;

public class Restaurant {

	private List<DishIngredient> dishIngredients;
	private final String name;
	private final ContactInfo contactInfo;
	private OpeningPeriod[] openingHours;
	private String bankAccountNr;

	public Restaurant(String name, ContactInfo contactInfo) {
		this.name = name;
		this.contactInfo = contactInfo;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}
}