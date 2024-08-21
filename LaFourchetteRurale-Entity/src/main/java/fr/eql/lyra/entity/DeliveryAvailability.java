package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class DeliveryAvailability implements Serializable {

    private final int id;
    private Integer memberId;
    private final Integer dayId;
    private final LocalDate selectionDate;
    private LocalDate withdrawalDate;


    //CONSTRUCTOR
    public DeliveryAvailability(int id, Integer dayId, LocalDate selectionDate) {
        this.id = id;
        this.dayId = dayId;
        this.selectionDate = selectionDate;
    }

    public DeliveryAvailability(int id, Integer memberId, Integer dayId, LocalDate selectionDate, LocalDate withdrawalDate) {
        this.id = id;
        this.memberId = memberId;
        this.dayId = dayId;
        this.selectionDate = selectionDate;
        this.withdrawalDate = withdrawalDate;
    }

    //GETTER

    public int getId() {
        return id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public Integer getDayId() {
        return dayId;
    }

    public LocalDate getSelectionDate() {
        return selectionDate;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    //SETTER
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    public void setWithdrawalDate(LocalDate withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
}
