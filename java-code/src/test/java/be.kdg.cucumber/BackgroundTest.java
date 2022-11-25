package be.kdg.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Tibo Lanneau
 * 30/10/2022
 */
public class BackgroundTest {

    @Given("Koeriers")
    public void koeriers(DataTable dataTable) {
        DeliDish.koeriers.clear();
        List<Map<String, String>> myMap = dataTable.asMaps();
        for(Map<String, String> map: myMap) {
            DeliDish.koeriers.add(new Koerier(
                    Integer.parseInt(map.get("koerier_id")),
                    map.get("naam"),
                    map.get("voornaam"),
                    Boolean.parseBoolean(map.get("beschikbaar")),
                    map.get("locatie")
            ));
        }
    }

    @Given("Bestellingen")
    public void bestellingen(DataTable dataTable) {
        DeliDish.bestellingen.clear();
        List<Map<String, String>> myMap = dataTable.asMaps();
        for(Map<String, String> map: myMap) {
            DeliDish.bestellingen.add(new Bestelling(
                    Integer.parseInt(map.get("bestelling_id")),
                    Integer.parseInt(map.get("restaurant_id")),
                    tryParseInt(map.get("leverOpdracht_id"))
            ));
        }
    }

    @Given("LeverOpdrachten")
    public void leveropdrachten(DataTable dataTable) {
        DeliDish.leverOpdrachten.clear();
        List<Map<String, String>> myMap = dataTable.asMaps();
        for(Map<String, String> map: myMap) {
            DeliDish.leverOpdrachten.add(new LeverOpdracht(
                    Integer.parseInt(map.get("leverOpdracht_id")),
                    map.get("afleveradres"),
                    tryParseInt(map.get("koerier_id")),
                    Integer.parseInt(map.get("bestelling_id"))
            ));
        }
    }

    public Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
    }

    @Given("StatusWijziging")
    public void statuswijziging(DataTable dataTable) {
        DeliDish.statusWijzigingen.clear();
        List<Map<String, String>> myMap = dataTable.asMaps();
        for(Map<String, String> map: myMap) {
            DeliDish.statusWijzigingen.add(new StatusWijziging(
                    Integer.parseInt(map.get("statusWijziging_id")),
                    Integer.parseInt(map.get("leverOpdracht_id")),
                    LocalDateTime.parse(map.get("created"), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    map.get("status")
            ));
        }
    }
}
