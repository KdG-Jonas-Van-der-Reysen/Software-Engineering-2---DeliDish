package be.kdg.delidish.business.domain.person;

import be.kdg.delidish.business.domain.common.ContactInfo;

public class Person {

	private int personId;
	private ContactInfo contactIno;
	private String firstName;
	private String lastName;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
}