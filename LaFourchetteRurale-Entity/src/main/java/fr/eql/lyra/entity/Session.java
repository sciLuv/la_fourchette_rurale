package fr.eql.lyra.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Session implements Serializable {
    private int id;
    private String token;
    private Timestamp timestamp;
    private int deliveryManId;

    public Session() {
    }

    public Session(int id, String token, Timestamp timestamp, int deliveryManId) {
        this.id = id;
        this.token = token;
        this.timestamp = timestamp;
        this.deliveryManId = deliveryManId;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getDeliveryManId() {
        return deliveryManId;
    }
}
