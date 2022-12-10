package be.kdg.delidish.business.domain.common;

public class City {

	private String postalCode;
	private String name;
	private int attribute;

	public City(String postalCode, String name, int attribute) {
		this.postalCode = postalCode;
		this.name = name;
		this.attribute = attribute;
	}
}