package be.kdg.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

/**
 * Tibo Lanneau
 * 28/10/2022
 */
public class AfhalenTest {
    LeverOpdracht leverOpdracht;
    @Given("koerier {int} is in het restaurant")
    public void koerierIsInHetRestaurant(int koerierNummer) {
        DeliDish.statusWijzigingen.size();
    }

    @When("status leverOpdracht {int} is {string}")
    public void statusLeverOpdrachtIs(int leverNummer, String status) {
//        leverOpdracht.aanpassenStatus(leverNummer, status);
    }

    @And("de koerier {int} geeft aan dat hij de maaltijd heeft afgehaald")
    public void deKoerierGeeftAanDatHijDeMaaltijdHeeftAfgehaald(int koerierNummer) {
    }
}
