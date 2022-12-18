package be.kdg.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class bestellingAannemenTest {
    @Given("koerier {int} wilt leverOpdracht {int} aannemen")
    public void koerierWiltLeverOpdrachtAannemen(int arg0, int arg1) {
    }

    @When("koerier {int} klikt op aannemen bij leverOpdracht {int}")
    public void koerierKliktOpAannemenBijLeverOpdracht(int arg0, int arg1) {
    }

    @Then("verandert koerier_id van leverOpdracht {int} naar {int}")
    public void verandertKoerier_idVanLeverOpdrachtNaar(int arg0, int arg1) {
    }

    @And("wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht {int},  created nu, status {string}")
    public void wordtErEenNieuweStatusWijzigingAangemaaktVoorLeverOpdrachtCreatedNuStatus(int arg0, String arg1) {
    }

    @And("verandert beschikbaar van koerier {int} naar false")
    public void verandertBeschikbaarVanKoerierNaarFalse(int arg0) {
    }

    @And("het systeem geeft de gegevens van leverOpdracht {int} weer")
    public void hetSysteemGeeftDeGegevensVanLeverOpdrachtWeer(int arg0) {
    }

    @When("de beschikbaarheid van koerier {int} staat op {string}")
    public void deBeschikbaarheidVanKoerierStaatOp(int arg0, String arg1) {
    }

    @Then("nieuwe leverOpdracht {int} wordt niet aangenomen")
    public void nieuweLeverOpdrachtWordtNietAangenomen(int arg0) {
    }
}
