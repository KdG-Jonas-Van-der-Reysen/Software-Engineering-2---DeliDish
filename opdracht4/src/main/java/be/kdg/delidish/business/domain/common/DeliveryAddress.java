package be.kdg.delidish.business.domain.common;

import be.kdg.delidish.business.domain.person.Customer;

public class DeliveryAddress extends Address {
    private Customer customer;
    public DeliveryAddress(City city, String street, String number, Position position, String country, Customer customer) {
        super(city, street, number, position, country);
        this.customer = customer;
    }
}
