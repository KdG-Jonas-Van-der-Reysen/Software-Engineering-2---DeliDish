package be.kdg.cucumber;

public enum BestelStatus {
    // De bestelling werd geplaatst door de klant
    NIEUW(0),

    // Het restaurant heeft de bestelling geaccepteerd
    OPEN(0),

    // De koerier heeft de bestelling (leveropdracht) aangenomen
    AF_TE_HALEN(10),

    // Het gerecht is klaar en kan bezorgd worden
    KLAAR(0),

    // Het gerecht werd op tijd afgehaald
    OP_TIJD_AFGEHAALD(10),

    // Het gerecht werd max 10 min te laat afgehaald
    AFGEHAALD(0),

    // Het gerecht werd te laat afgehaald
    TE_LAAT_AFGEHAALD(-10),

    // Het gerecht werd op tijd bezorgd
    OP_TIJD_BEZORGD(10),

    // Het gerecht werd max 10 min te laat bezorgd
    BEZORGD(0),

    // Het gerecht werd te laat bezorgd
    TE_LAAT_BEZORGD(-10);

    private final int puntenResultaat;

    BestelStatus(int puntenResultaat) {
        this.puntenResultaat = puntenResultaat;
    }

    public int getPuntenResultaat() {
        return puntenResultaat;
    }

}
