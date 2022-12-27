package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.domain.common.ContactInfo;

public class Person {

	private ContactInfo contactInfo;
	private String firstName;
	private String lastName;

	public Person(String firstName, String lastName, ContactInfo contactInfo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInfo = contactInfo;
	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
}