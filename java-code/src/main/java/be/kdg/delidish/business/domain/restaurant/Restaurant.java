package be.kdg.delidish.business.domain.restaurant;

import be.kdg.delidish.business.domain.common.ContactInfo;

import java.util.List;

public class Restaurant {

	private List<Dish> dishes;
	private String name;
	private ContactInfo contactInfo;
	private OpeningPeriod[] openingHours;
	private String bankAccountNr;

	public ContactInfo getContactInfo() {
		return contactInfo;
	}
}