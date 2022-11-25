package be.kdg.cucumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Tibo Lanneau
 * 28/10/2022
 */
public class StatusWijziging {

    private int statusWijzigingId;
    private int leverOpdrachtId;
    private LocalDateTime created;
    private String status;

    public StatusWijziging(int statusWijzigingId, int leverOpdrachtId, LocalDateTime created, String status) {
        this.statusWijzigingId = statusWijzigingId;
        this.leverOpdrachtId = leverOpdrachtId;
        this.created = created;
        this.status = status;
    }

    public int getStatusWijzigingId() {
        return statusWijzigingId;
    }

    public int getLeverOpdrachtId() {
        return leverOpdrachtId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public String getStatus() {
        return status;
    }
}
