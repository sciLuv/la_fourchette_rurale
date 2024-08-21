package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Package implements Serializable {
    private int packageId;
    private Integer deliveryFailureId;
    private int tourneeId;
    private LocalDate expectedDelivery;
    private LocalDate actualDelivery;
    private String packageNumber;

    //CONSTRUCTOR
    public Package(int packageId, Integer deliveryFailureId, int tourneeId,
                   LocalDate expectedDelivery, LocalDate actualDelivery,
                   String packageNumber) {
        this.packageId = packageId;
        this.deliveryFailureId = deliveryFailureId;
        this.tourneeId = tourneeId;
        this.expectedDelivery = expectedDelivery;
        this.actualDelivery = actualDelivery;
        this.packageNumber = packageNumber;
    }

    //GETTER

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public Integer getDeliveryFailureId() {
        return deliveryFailureId;
    }

    public void setDeliveryFailureId(Integer deliveryFailureId) {
        this.deliveryFailureId = deliveryFailureId;
    }

    public int getTourneeId() {
        return tourneeId;
    }

    public void setTourneeId(int tourneeId) {
        this.tourneeId = tourneeId;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public LocalDate getActualDelivery() {
        return actualDelivery;
    }

    public void setActualDelivery(LocalDate actualDelivery) {
        this.actualDelivery = actualDelivery;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }
}
