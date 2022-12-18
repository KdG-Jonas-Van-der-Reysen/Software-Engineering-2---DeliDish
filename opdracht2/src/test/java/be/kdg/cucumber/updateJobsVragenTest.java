package be.kdg.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class updateJobsVragenTest {
    @Given("Koeriers")
    public void koeriers(DataTable koeriersData) {
        List<Map<String, String>> myMap = koeriersData.asMaps();

        for (Map<String, String> map : myMap) {
            DeliDish.koeriers.add(new Koerier(
                    DeliDish.tryParseInt(map.get("koerier_id")),
                    map.get("naam"),
                    map.get("voornaam"),
                    Boolean.parseBoolean(map.get("beschikbaar")),
                    map.get("locatie")
            ));
        }
    }

    @Given("Bestellingen")
    public void bestellingen(DataTable bestellingenData) {
        List<Map<String, String>> rows = bestellingenData.asMaps();

        for (Map<String, String> bestelling : rows) {
            DeliDish.bestellingen.add(new Bestelling(
                DeliDish.tryParseInt(bestelling.get("bestelling_id")),
                DeliDish.tryParseInt(bestelling.get("restaurant_id")),
                DeliDish.tryParseInt(bestelling.get("leverOpdracht_id")) == 0 ? null : DeliDish.tryParseInt(bestelling.get("leverOpdracht_id"))
            ));
        }
    }

    @Given("LeverOpdrachten")
    public void leveropdrachten(DataTable leverOpdrachtenData) {
        List<Map<String, String>> rows = leverOpdrachtenData.asMaps();

        for (Map<String, String> leverOpdracht : rows) {
            DeliDish.leverOpdrachten.add(new LeverOpdracht(
                    DeliDish.tryParseInt(leverOpdracht.get("leverOpdracht_id")),
                    leverOpdracht.get("afleverAdres"),
                    DeliDish.tryParseInt(leverOpdracht.get("koerier_id")),
                    DeliDish.tryParseInt(leverOpdracht.get("bestelling_id"))
            ));

            // Get order by leverOpdracht.bestelling_id
            Bestelling bestelling = DeliDish.bestellingen.stream()
                    .filter(b -> b.getBestellingId() == DeliDish.tryParseInt(leverOpdracht.get("bestelling_id")))
                    .findFirst()
                    .orElse(null);

            if(bestelling != null) {
                bestelling.setLeverOpdrachtId(DeliDish.tryParseInt(leverOpdracht.get("leverOpdracht_id")));
            }
        }
    }

    @Given("StatusWijziging")
    public void statuswijziging(DataTable statusWijzigingenData) {
        List<Map<String, String>> rows = statusWijzigingenData.asMaps();

        for (Map<String, String> statusWijziging : rows) {
            DeliDish.statusWijzigingen.add(new StatusWijziging(
                    DeliDish.tryParseInt(statusWijziging.get("statusWijziging_id")),
                    DeliDish.tryParseInt(statusWijziging.get("leverOpdracht_id")),
                    DeliDish.tryParseLocalDateTime(statusWijziging.get("created")),
                    BestelStatus.valueOf(statusWijziging.get("status"))
            ));
        }
    }

    @Given("Er zijn {int} bestellingen waarvoor de koerier in aanmerking komt")
    public void erZijnBestellingenWaarvoorDeKoerierInAanmerkingKomt(int arg0) {
        assertEquals(arg0, DeliDish.bestellingen.size());
    }

    @When("de koerier drukt op de refresh knop van de bestellingen")
    public void deKoerierDruktOpDeRefreshKnopVanDeBestellingen() {
        // Nothing special should happen here
    }

    @Then("toont het systeem de {int} bestellingen waarvan de status {string} is")
    public void toontHetSysteemDeBestellingenWaarvanDeStatusIs(int aantalBestellingen, String status) {
        System.out.println("test");
        List<Bestelling> matchingOrders = DeliDish.bestellingen.stream()
                .peek(bestelling -> System.out.println(bestelling.getBestellingId()))
                .filter(bestelling -> DeliDish.getStatus(bestelling.getBestellingId()).equals(BestelStatus.valueOf(status)))
                .collect(Collectors.toList());

        assertEquals(aantalBestellingen, matchingOrders.size());
    }
    
}
