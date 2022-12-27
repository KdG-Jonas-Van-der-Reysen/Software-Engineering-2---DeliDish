package be.kdg.delidish.business.domain.common;

public class Address {

	private final City city;
	private final String street;
	private final String number;
	private final Position position;
	private final String country;
	private final int attribute;

	public Address(City city, String street, String number, Position position, String country, int attribute) {
		this.city = city;
		this.street = street;
		this.number = number;
		this.position = position;
		this.country = country;
		this.attribute = attribute;
	}

	public Position getPosition() {
		return position;
	}
}