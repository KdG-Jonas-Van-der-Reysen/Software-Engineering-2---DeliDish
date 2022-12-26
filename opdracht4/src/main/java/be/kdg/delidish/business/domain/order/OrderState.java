package be.kdg.delidish.business.domain.order;

public enum OrderState {
	ORDER_PLACED,
	ACCEPTED_BY_COURIER,
	DISHES_PREPARED,
	UNDERWAY_FOR_DELIVERY,
	DELIVERED
}