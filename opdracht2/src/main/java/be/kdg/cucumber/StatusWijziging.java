package be.kdg.cucumber;

import java.time.LocalDateTime;

public class StatusWijziging {
    private int statusWijzigingId;
    private int leverOpdrachtId;
    private LocalDateTime created;
    private BestelStatus status;

    public StatusWijziging(int statusWijzigingId, int leverOpdrachtId, LocalDateTime created, BestelStatus status) {
        this.statusWijzigingId = statusWijzigingId;
        this.leverOpdrachtId = leverOpdrachtId;
        this.created = created;
        this.status = status;
    }

    public int getStatusWijzigingId() {
        return statusWijzigingId;
    }

    public void setStatusWijzigingId(int statusWijzigingId) {
        this.statusWijzigingId = statusWijzigingId;
    }

    public int getLeverOpdrachtId() {
        return leverOpdrachtId;
    }

    public void setLeverOpdrachtId(int leverOpdrachtId) {
        this.leverOpdrachtId = leverOpdrachtId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BestelStatus getStatus() {
        return status;
    }

    public void setStatus(BestelStatus status) {
        this.status = status;
    }
}
