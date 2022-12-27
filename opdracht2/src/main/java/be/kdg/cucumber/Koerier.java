package be.kdg.cucumber;

public class Koerier {
    private int koerierId;
    private String naam;
    private String voornaam;
    private boolean beschikbaar;
    private String locatie;

    public Koerier(int koerierId, String naam, String voornaam, boolean beschikbaar, String locatie) {
        this.koerierId = koerierId;
        this.naam = naam;
        this.voornaam = voornaam;
        this.beschikbaar = beschikbaar;
        this.locatie = locatie;
    }

    public int getKoerierId() {
        return koerierId;
    }

    public void setKoerierId(int koerierId) {
        this.koerierId = koerierId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }
}
