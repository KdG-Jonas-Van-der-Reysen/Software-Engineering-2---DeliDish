package be.kdg.delidish.business.domain.common;

public class Address {

	private final City city;
	private final String street;
	private final String number;
	private final Position position;
	private final String country;

	public Address(City city, String street, String number, Position position, String country) {
		this.city = city;
		this.street = street;
		this.number = number;
		this.position = position;
		this.country = country;
	}

	public Position getPosition() {
		return position;
	}
}