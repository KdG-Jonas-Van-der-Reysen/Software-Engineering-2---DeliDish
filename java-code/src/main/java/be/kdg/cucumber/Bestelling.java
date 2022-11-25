package be.kdg.cucumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Tibo Lanneau
 * 28/10/2022
 */
public class Bestelling {
    private int bestellingId;
    private int restaurantId;
    private Integer leverOpdrachtId;

    public Bestelling(int bestelling_id, int restaurant_id, Integer leverOpdracht_id) {
        this.bestellingId = bestelling_id;
        this.restaurantId = restaurant_id;
        this.leverOpdrachtId = leverOpdracht_id;
    }

    @Override
    public String toString() {
        return "Bestelling{" +
                "bestellingId=" + bestellingId +
                ", restaurantId=" + restaurantId +
                ", leverOpdrachtId=" + leverOpdrachtId +
                '}';
    }
}
