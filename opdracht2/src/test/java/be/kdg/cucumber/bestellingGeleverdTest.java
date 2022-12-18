package be.kdg.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class bestellingGeleverdTest {
    @Given("koerier {int} geeft aan dat bestelling {int} afgeleverd is")
    public void koerierGeeftAanDatBestellingAfgeleverdIs(int arg0, int arg1) {
    }

    @When("koerier {int} klikt op {string} bij bestelling {int}")
    public void koerierKliktOpBijBestelling(int arg0, String arg1, int arg2) {
    }

    @And("verandert beschikbaar van koerier {int} naar true")
    public void verandertBeschikbaarVanKoerierNaarTrue(int arg0) {
    }
}
