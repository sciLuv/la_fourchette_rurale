package fr.eql.lyra.entity;

import java.io.Serializable;

public class DeliveryFailure implements Serializable {
    private final int deliveryFailureId;
    private String reason;

    public DeliveryFailure(int deliveryFailureId, String reason) {
        this.deliveryFailureId = deliveryFailureId;
        this.reason = reason;
    }

    public int getDeliveryFailureId() {
        return deliveryFailureId;
    }

    public String getReason() {
        return reason;
    }
}
