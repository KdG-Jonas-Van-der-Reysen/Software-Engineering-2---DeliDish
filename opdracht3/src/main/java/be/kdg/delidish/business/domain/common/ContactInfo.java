package be.kdg.delidish.business.domain.common;

public class ContactInfo {

	private final Address address;
	private final String email;
	private final String tel;

	public ContactInfo(Address address, String email, String tel) {
		this.address = address;
		this.email = email;
		this.tel = tel;
	}

	public Address getAddress() {
		return address;
	}
}