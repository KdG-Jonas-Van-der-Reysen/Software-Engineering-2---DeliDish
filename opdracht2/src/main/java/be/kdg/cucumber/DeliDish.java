package be.kdg.cucumber;

import java.time.LocalDateTime;
import java.util.*;

public class DeliDish {
    public static final List<Koerier> koeriers = new ArrayList<>();
    public static final List<Bestelling> bestellingen = new ArrayList<>();
    public static final List<LeverOpdracht> leverOpdrachten = new ArrayList<>();
    public static final List<StatusWijziging> statusWijzigingen = new ArrayList<>();

    public static BestelStatus getStatus(int bestellingId) {
        System.out.println("Running get status");
        // First, get the leverOpdrachtId
        int leverOpdrachtId = 0;

        for (Bestelling bestelling : bestellingen) {
            if (bestelling.getBestellingId() == bestellingId) {
                leverOpdrachtId = bestelling.getLeverOpdrachtId();
                break;
            }
        }
        System.out.println("got the status: " + leverOpdrachtId);

        // Then, get the status
        for (LeverOpdracht leverOpdracht : leverOpdrachten) {
            if (leverOpdracht.getLeverOpdrachtId() == leverOpdrachtId) {
                List<StatusWijziging> sw =  leverOpdracht.getStatusWijzigingen();
                return sw.get(sw.size() - 1).getStatus();
            }
        }

        return null;
    }

    public static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static LocalDateTime tryParseLocalDateTime(String value) {
        return LocalDateTime.of(
                DeliDish.tryParseInt(value.substring(6, 10)),
                DeliDish.tryParseInt(value.substring(3, 5)),
                DeliDish.tryParseInt(value.substring(0, 2)),
                DeliDish.tryParseInt(value.substring(11, 13)),
                DeliDish.tryParseInt(value.substring(14, 16))
        );
    }
}
