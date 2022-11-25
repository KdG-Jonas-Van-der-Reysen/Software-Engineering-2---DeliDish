package be.kdg.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tibo Lanneau
 * 28/10/2022
 */
public class AannemenTest {

    private Koerier koerier;
    private LeverOpdracht leverOpdracht;
    private StatusWijziging statusWijziging;

    @Given("koerier {int} wilt leverOpdracht {int} aannemen")
    public void koerierWiltLeverOpdrachtAannemen(int koerierNummer, int leverNummer) {
        DeliDish.statusWijzigingen.size();
        koerier = DeliDish.koeriers.get(koerierNummer-1);
        leverOpdracht = DeliDish.leverOpdrachten.get(leverNummer-1);
    }

    @When("koerier {int} klikt op aannemen bij leverOpdracht {int}")
    public void koerierKliktOpAannemenBijLeverOpdracht(int koerierNummer, int leverNummer) throws Throwable {
        if(koerier.isBeschikbaar()) {
            statusWijziging = new StatusWijziging(DeliDish.statusWijzigingen.size() + 1, leverOpdracht.getLeverOpdrachtId(), LocalDateTime.now(), "AF_TE_HALEN");
            leverOpdracht.setKoerierId(koerier.getKoerierId());
            DeliDish.statusWijzigingen.add(statusWijziging);
            koerier.getLeverOpdrachten().add(leverOpdracht);
            koerier.setBeschikbaar(false);
        }
    }

    @Then("verandert koerier_id van leverOpdracht {int} naar {int}")
    public void verandertKoerier_idVanLeverOpdrachtNaar(int leverNummer, int koerierNummer) {
        assertEquals(koerierNummer, (DeliDish.leverOpdrachten.get(leverNummer-1).getKoerierId()));
    }

    @And("wordt er een nieuwe StatusWijziging aangemaakt voor leverOpdracht {int},  created nu, status {string}")
    public void wordtErEenNieuweStatusWijzigingAangemaaktVoorLeverOpdrachtCreatedNuStatus(int leverNummer, String status) throws Throwable {
        assertEquals(statusWijziging.getLeverOpdrachtId(), leverNummer);
        assertEquals(statusWijziging.getStatus(), status);
    }

    @And("verandert beschikbaar van koerier {int} naar false")
    public void verandertBeschikbaarVanKoerierNaarFalse(int koerierNummer) {
        assertFalse(DeliDish.koeriers.get(koerierNummer-1).isBeschikbaar());
    }

    @And("het systeem geeft de gegevens van leverOpdracht {int} weer")
    public void hetSysteemGeeftDeGegevensVanLeverOpdrachtWeer(int leverNummer) {
        System.out.println(DeliDish.leverOpdrachten.get(leverNummer-1));
    }

    @When("de beschikbaarheid van koerier {int} staat op {string}")
    public void koerierStaatOp(int koerierNummer, String status) {
        //List<Koerier> koeriers = koerier.getKoeriers();
        //assertEquals(status, koeriers.get(koerierNummer).getBeschikbaarheid(status), "beschikbaar moet op \"false\" staan");
    }

    @Then("nieuwe leverOpdracht {int} wordt niet aangenomen")
    public void nieuweLeverOpdrachtWordtNietAangenomen(int leverNummer) {
        //List<StatusWijziging> statusWijzigingen = statusWijziging.getStatusWijzigingen();
        //assertEquals("OPEN", statusWijzigingen.get(leverNummer).getStatus(), "status moet hetzelfde zijn");
    }

}
