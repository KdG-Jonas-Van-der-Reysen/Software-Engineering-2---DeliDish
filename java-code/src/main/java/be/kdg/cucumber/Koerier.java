package be.kdg.cucumber;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Tibo Lanneau
 * 28/10/2022
 */
public class Koerier {

    private int koerierId;
    private String naam;
    private String voornaam;
    private boolean beschikbaar = true;
    private String locatie;

    private List<LeverOpdracht> leverOpdrachten = new LinkedList<>();

    public Koerier(int koerierId, String naam, String voornaam, boolean beschikbaar, String locatie) {
        this.koerierId = koerierId;
        this.naam = naam;
        this.voornaam = voornaam;
        this.beschikbaar = beschikbaar;
        this.locatie = locatie;
    }

    /*public void toekennenOpdracht(int koerierNummer, String event, int leverNummer) {
        Koerier koerier = DeliDish.koeriers.get(koerierNummer);
        if (event.equals("aannemen")) {
            // leverOpdrachten.add(leverNummer);
            beschikbaar = false;

        }
    }*/

    public int getKoerierId() {
        return koerierId;
    }

    public List<LeverOpdracht> getLeverOpdrachten() {
        return leverOpdrachten;
    }

    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }
}
