package be.kdg.cucumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Tibo Lanneau
 * 28/10/2022
 */
public class LeverOpdracht {
    private int leverOpdrachtId;
    private String afleverAdres;
    private Integer koerierId;
    private int bestellingId;

    private List<LeverOpdracht> leverOpdrachten = new ArrayList<>();

    public LeverOpdracht(int leverOpdrachtId, String afleverAdres, Integer koerierId, int bestellingId) {
        this.leverOpdrachtId = leverOpdrachtId;
        this.afleverAdres = afleverAdres;
        this.koerierId = koerierId;
        this.bestellingId = bestellingId;
    }

    public void toekennenOpdracht(int koerierNummer, String event, int leverNummer) {
        LeverOpdracht leverOpdracht = leverOpdrachten.get(leverNummer);
        if (event.equals("aannemen")) {
            this.koerierId = koerierNummer;
            //status.add(new StatusWijziging(leverNummer, LocalDateTime.now(), "AF_TE_HALEN"));
        }
    }

    public void aanpassenStatus(int leverNummer, String status) {
        if (status.toUpperCase().equals("KLAAR")) {
            //this.status.add(new StatusWijziging(leverNummer, LocalDateTime.now(), status));
        }
    }

    public int getLeverOpdrachtId() {
        return leverOpdrachtId;
    }

    public Integer getKoerierId() {
        return koerierId;
    }

    public void setKoerierId(int koerierId) {
        this.koerierId = koerierId;
    }

    public List<LeverOpdracht> getLeverOpdrachten() {
        return leverOpdrachten;
    }

    public String getInfo(int leverNummer) {
        LeverOpdracht leverOpdracht = leverOpdrachten.get(leverNummer);
        //StatusWijziging statusWijziging = status.get(leverNummer);
        //return String.format("LeverOpdracht_id: %d\nKoerierId: %d\nStatus: %s", leverOpdracht.getLeverOpdrachtId(), leverOpdracht.getKoerierId(), statusWijziging.getStatus());
        return " ";
    }
}
