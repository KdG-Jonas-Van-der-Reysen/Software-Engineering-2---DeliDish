package be.kdg.delidish.business.domain.person;

public enum EventType {
    ORDER_ACCEPTED(50),
    TIMELY_PICKUP(50),
    LATE_PICKUP(-20),
    TIMELY_DELIVERY(50),
    LATE_DELIVERY(-20),
    DAILY_REDUCTION(-10);


    public final int points;
    private EventType(int points) {
        this.points = points;
    }
}