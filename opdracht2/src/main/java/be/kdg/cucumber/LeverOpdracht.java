package be.kdg.cucumber;

import java.util.ArrayList;
import java.util.List;

public class LeverOpdracht {
    private int leverOpdrachtId;
    private String afleverAdres;
    private int koerierId;
    private int bestellingId;

    private List<StatusWijziging> statusWijzigingen;

    public LeverOpdracht(int leverOpdrachtId, String afleverAdres, int koerierId, int bestellingId) {
        this.leverOpdrachtId = leverOpdrachtId;
        this.afleverAdres = afleverAdres;
        this.koerierId = koerierId;
        this.bestellingId = bestellingId;
        statusWijzigingen = new ArrayList<>();
    }

    public int getLeverOpdrachtId() {
        return leverOpdrachtId;
    }

    public void setLeverOpdrachtId(int leverOpdrachtId) {
        this.leverOpdrachtId = leverOpdrachtId;
    }

    public String getAfleverAdres() {
        return afleverAdres;
    }

    public void setAfleverAdres(String afleverAdres) {
        this.afleverAdres = afleverAdres;
    }

    public int getKoerierId() {
        return koerierId;
    }

    public void setKoerierId(int koerierId) {
        this.koerierId = koerierId;
    }

    public int getBestellingId() {
        return bestellingId;
    }

    public void setBestellingId(int bestellingId) {
        this.bestellingId = bestellingId;
    }

    public List<StatusWijziging> getStatusWijzigingen() {
        return statusWijzigingen;
    }

    public void setStatusWijzigingen(List<StatusWijziging> statusWijzigingen) {
        this.statusWijzigingen = statusWijzigingen;
    }
}
