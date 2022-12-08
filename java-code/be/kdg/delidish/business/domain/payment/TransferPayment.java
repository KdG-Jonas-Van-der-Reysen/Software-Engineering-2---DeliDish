package be.kdg.delidish.business.domain.payment;

import java.util.*;
import be.kdg.delidish.business.domain.order.*;
import be.kdg.delidish.business.domain.person.*;

public class TransferPayment extends Payment {

	private List<Order> orders;
	private Person person;

}