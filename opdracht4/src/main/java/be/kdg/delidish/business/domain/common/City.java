package be.kdg.delidish.business.domain.common;

public class City {

	private final int postalCode;
	private final String name;
	private final String country;

	public City(int postalCode, String name, String country) {
		this.postalCode = postalCode;
		this.name = name;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}
}