package be.kdg.delidish.business.domain.payment;

import be.kdg.delidish.business.domain.order.Order;

public class DirectPayment extends Payment {

	private Order order;
	private PaymentType paymentType;

}