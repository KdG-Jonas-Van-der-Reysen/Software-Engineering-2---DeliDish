package be.kdg.delidish.business.domain.common;

public class City {

	private int postalCode;
	private String name;
	private String country;

	public City(int postalCode, String name, String country) {
		this.postalCode = postalCode;
		this.name = name;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}
}