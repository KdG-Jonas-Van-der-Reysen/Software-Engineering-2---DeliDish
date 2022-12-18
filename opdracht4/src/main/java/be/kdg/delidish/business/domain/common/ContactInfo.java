package be.kdg.delidish.business.domain.common;

public class ContactInfo {

	private Address address;
	private String email;
	private String tel;

	public ContactInfo(Address address, String email, String tel) {
		this.address = address;
		this.email = email;
		this.tel = tel;
	}

	public Address getAddress() {
		return address;
	}
}