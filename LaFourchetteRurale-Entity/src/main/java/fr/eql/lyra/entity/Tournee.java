package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tournee implements Serializable {
    private int tourneeId;
    private int deliveryManId;
    private LocalDate date;
    private LocalDateTime dateTournee;
    private LocalDateTime finTournee;

    public Tournee() {
    }

    public Tournee(int tourneeId, int deliveryManId, LocalDate date, LocalDateTime dateTournee, LocalDateTime finTournee) {
        this.tourneeId = tourneeId;
        this.deliveryManId = deliveryManId;
        this.date = date;
        this.dateTournee = dateTournee;
        this.finTournee = finTournee;
    }

    public int getTourneeId() {
        return tourneeId;
    }

    public int getDeliveryManId() {
        return deliveryManId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getDateTournee() {
        return dateTournee;
    }

    public LocalDateTime getFinTournee() {
        return finTournee;
    }

    public void setTourneeId(int tourneeId) {
        this.tourneeId = tourneeId;
    }

    public void setDeliveryManId(int deliveryManId) {
        this.deliveryManId = deliveryManId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDateTournee(LocalDateTime dateTournee) {
        this.dateTournee = dateTournee;
    }

    public void setFinTournee(LocalDateTime finTournee) {
        this.finTournee = finTournee;
    }
}


