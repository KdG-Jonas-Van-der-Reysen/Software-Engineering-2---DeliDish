package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.domain.common.Address;

public class Customer extends Person {

	private Address deliveryAdresses;

	public Customer(String firstName, String lastName) {
		super(firstName, lastName);
	}
}