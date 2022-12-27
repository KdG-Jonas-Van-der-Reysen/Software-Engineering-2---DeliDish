package be.kdg.delidish.business.domain.common;

public class City {

	private final String postalCode;
	private final String name;
	private final int attribute;

	public City(String postalCode, String name, int attribute) {
		this.postalCode = postalCode;
		this.name = name;
		this.attribute = attribute;
	}
}