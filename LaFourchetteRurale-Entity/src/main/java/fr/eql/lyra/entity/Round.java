package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Round implements Serializable {
    private int roundId;
    private int deliveryManId;
    private String number;
    private LocalDate date;
    private LocalDateTime roundStart;
    private LocalDateTime roundEnd;

    //CONSTRUCTOR
    public Round(int roundId, int deliveryManId, String number, LocalDate date,
                 LocalDateTime roundStart, LocalDateTime roundEnd) {
        this.roundId = roundId;
        this.deliveryManId = deliveryManId;
        this.number = number;
        this.date = date;
        this.roundStart = roundStart;
        this.roundEnd = roundEnd;
    }

    //CONSTRUCTOR
    public int getRoundId() {
        return roundId;
    }

    public int getDeliveryManId() {
        return deliveryManId;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getRoundStart() {
        return roundStart;
    }

    public LocalDateTime getRoundEnd() {
        return roundEnd;
    }
}
