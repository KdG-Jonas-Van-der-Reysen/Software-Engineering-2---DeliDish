package be.kdg.delidish.business.domain.order;

import be.kdg.delidish.business.domain.common.Address;
import be.kdg.delidish.business.observer.Observable;
import be.kdg.delidish.business.observer.Observer;
import be.kdg.delidish.business.observer.OrderStateObserver;
import be.kdg.delidish.business.domain.person.Courier;
import be.kdg.delidish.business.domain.person.Customer;
import be.kdg.delidish.business.domain.person.EventType;
import be.kdg.delidish.business.domain.restaurant.Restaurant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Order implements Observable {

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

    private List<Observer> observers;

    public Order(Restaurant restaurant, LocalDateTime timePlaced, OrderState state, List<OrderLine> orderLines, String description) {
        this.restaurant = restaurant;
        this.timePlaced = timePlaced;
        this.state = state;
        this.orderLines = orderLines;
        this.description = description;

        orderEvents = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void assignCourier(Courier courier) {
        addObserver(new OrderStateObserver(courier));
        this.courier = courier;
    }

    public void assignCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addEvent(OrderEvent event) {
        orderEvents.add(event);

        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    // Getters & setters
    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public LocalDateTime getTimePlaced() {
        return timePlaced;
    }
    public int getProductionTime() {
        // Get order line with the highest production time
        return orderLines.stream().mapToInt(OrderLine::getProductionTime).max().orElse(0);
    }
    public int getMaximumDeliveryTime() {
        // Get order line with the lowest delivery time
        return orderLines.stream().mapToInt(OrderLine::getMaximumDeliveryTime).max().orElse(0);
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
        //orderLines.stream().map(OrderLine::getMinutesBeforeCold).forEach(System.out::println);
        Optional<OrderLine> lowestMinutesBeforeCold = orderLines.stream().min(Comparator.comparingInt(OrderLine::getMinutesBeforeCold));

        return lowestMinutesBeforeCold.map(orderLine -> timePlaced.plusMinutes(orderLine.getMinutesBeforeCold())).orElse(timePlaced);
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getTimeSelected() {
        return orderEvents.stream().filter(orderEvent -> orderEvent.getState() == (EventType.ORDER_ACCEPTED)).findFirst().get().getTime();
    }
    public Courier getCourier() {
        return courier;
    }

    // Publisher / subcriber pattern functions
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public OrderEvent getCurrentState() {
        return orderEvents.get(orderEvents.size()-1);
    }

    // Tostring
    @Override
    public String toString() {
        return " - Order met gerechten " + orderLines;
    }
}