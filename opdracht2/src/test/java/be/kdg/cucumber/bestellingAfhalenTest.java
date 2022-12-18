package be.kdg.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class bestellingAfhalenTest {
    @Given("koerier {int} is in het restaurant")
    public void koerierIsInHetRestaurant(int arg0) {
    }

    @When("status leverOpdracht {int} is {string}")
    public void statusLeverOpdrachtIs(int arg0, String arg1) {
    }

    @And("de koerier {int} geeft aan dat hij de maaltijd heeft afgehaald")
    public void deKoerierGeeftAanDatHijDeMaaltijdHeeftAfgehaald(int arg0) {
    }
}
