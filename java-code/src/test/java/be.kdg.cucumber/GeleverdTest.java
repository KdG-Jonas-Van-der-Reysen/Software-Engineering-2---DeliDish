package be.kdg.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tibo Lanneau
 * 28/10/2022
 */
public class GeleverdTest {
    Koerier koerier;
    @Given("koerier {int} geeft aan dat bestelling {int} afgeleverd is")
    public void koerierGeeftAanDatBestellingAfgeleverdIs(int koerierNummer, int arg1) {

    }

    @When("koerier {int} klikt op {string} bij bestelling {int}")
    public void koerierKliktOpBijBestelling(int koerierNummer, String arg1, int arg2) {

    }

    @And("verandert beschikbaar van koerier {int} naar true")
    public void verandertBeschikbaarVanKoerierNaarTrue(int koerierNummer) {
//        List<Koerier> koeriers = koerier.getKoeriers();
//        assertTrue(koeriers.get(koerierNummer).isBeschikbaar(), "beschikbaar moet op \"true\" staan");
    }
}
