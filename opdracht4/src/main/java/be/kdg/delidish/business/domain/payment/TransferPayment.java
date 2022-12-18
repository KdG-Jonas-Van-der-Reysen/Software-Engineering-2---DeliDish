package be.kdg.delidish.business.domain.payment;

import be.kdg.delidish.business.domain.order.Order;
import be.kdg.delidish.business.domain.person.Person;

import java.util.List;

public class TransferPayment extends Payment {

	private List<Order> orders;
	private Person person;

}