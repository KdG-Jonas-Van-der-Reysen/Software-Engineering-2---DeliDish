package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.Customer;
import be.kdg.delidish.business.domain.person.EventType;
import be.kdg.delidish.business.domain.restaurant.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Order {

    private int orderId;
    private List<OrderLine> orderLines;
    private List<OrderEvent> orderEvents;
    private Customer customer;
    private String description;
    private Courier courier;
    private Address deliveryAddress;
    private String deliveryInstructions;
    private LocalDateTime timePlaced;
    private OrderState state;
    private Restaurant restaurant;

    public Order(Restaurant restaurant, LocalDateTime timePlaced, OrderState state, List<OrderLine> orderLines, String description) {
        this.restaurant = restaurant;
        this.timePlaced = timePlaced;
        this.state = state;
        this.orderLines = orderLines;
        this.description = description;

        orderEvents = new ArrayList<>();
    }

    public void assignCourier(Courier courier) {
        this.courier = courier;
    }

    public void addEvent(OrderEvent event) {
        orderEvents.add(event);
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCourierId() {
        return courier.getPersonId();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDateTime getTimePlaced() {
        return timePlaced;
    }

    public int getProductionTime() {
        // Get order line with the highest production time
        Optional<OrderLine> maxProductionTime = orderLines.stream().max(Comparator.comparingInt(OrderLine::getProductionTime));

        return maxProductionTime.map(OrderLine::getProductionTime).orElse(0);
    }

    public int getMaximumDeliveryTime() {
        // Get order line with the lowest delivery time
        Optional<OrderLine> lowestMaxDeliveryTime = orderLines.stream().min(Comparator.comparingInt(OrderLine::getMaximumDeliveryTime));

        return lowestMaxDeliveryTime.map(OrderLine::getProductionTime).orElse(0);
    }

    public int getAverageDeliveryPoints(List<Courier> applicableCouriers) {
        // Eerst alle applicable koeriers ophalen -> gemiddelde berekenen
        return (int) applicableCouriers.stream().mapToDouble(Courier::getTotalDeliveryPoints).average().getAsDouble();
    }

    public LocalDateTime getExpectedDeliveryTime() {
        // Add the production time of the longest taking dish
        LocalDateTime orderReady = timePlaced.plusMinutes(getProductionTime());

        // Add the delivery time of the dish that should be delivered the quickest
        LocalDateTime orderShouldBeDelivered = orderReady.plusMinutes(getMaximumDeliveryTime());

        return orderShouldBeDelivered;
    }

    public LocalDateTime getOrderIsColdAt() {
        // Get the lowest minutes before cold
        Optional<OrderLine> lowestMinutesBeforeCold = orderLines.stream().min(Comparator.comparingInt(OrderLine::getMinutesBeforeCold));
        System.out.println("lowestMinutesBeforeCold = " + lowestMinutesBeforeCold);

        return lowestMinutesBeforeCold.map(orderLine -> timePlaced.plusMinutes(orderLine.getMinutesBeforeCold())).orElse(timePlaced);
    }

    @Override
    public String toString() {
        return " - [" + orderId + "] Order met gerechten " + orderLines;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimeSelected() {
        return orderEvents.stream().filter(orderEvent -> orderEvent.getState() == (EventType.ORDER_ACCEPTED)).findFirst().get().getTime();
    }
}