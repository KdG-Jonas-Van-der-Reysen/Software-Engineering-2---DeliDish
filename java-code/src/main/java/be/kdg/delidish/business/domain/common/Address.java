package be.kdg.delidish.business.domain.common;

public class Address {

	private City city;
	private String street;
	private String number;
	private Position position;
	private String country;
	private int attribute;

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