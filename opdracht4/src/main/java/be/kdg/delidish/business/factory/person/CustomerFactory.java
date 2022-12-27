package be.kdg.delidish.business.factory.person;


import be.kdg.delidish.business.domain.person.Customer;

public class CustomerFactory {
    public static Customer create(String firstName, String lastName) {
        return new Customer(firstName, lastName);
    }
}
