package be.kdg.cucumber;

public class Bestelling {
    private int bestellingId;
    private int restaurantId;
    private int leverOpdrachtId;

    public Bestelling(int bestellingId, int restaurantId, int leverOpdrachtId) {
        this.bestellingId = bestellingId;
        this.restaurantId = restaurantId;
        this.leverOpdrachtId = leverOpdrachtId;
    }

    public int getBestellingId() {
        return bestellingId;
    }

    public void setBestellingId(int bestellingId) {
        this.bestellingId = bestellingId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getLeverOpdrachtId() {
        return leverOpdrachtId;
    }

    public void setLeverOpdrachtId(int leverOpdrachtId) {
        this.leverOpdrachtId = leverOpdrachtId;
    }


}
