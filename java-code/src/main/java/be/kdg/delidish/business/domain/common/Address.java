package be.kdg.delidish.business.domain.common;

public class Address {

	private City city;
	private String street;
	private String number;
	private Position position;
	private String country;
	private int attribute;

	public Position getPosition() {
		return position;
	}
}