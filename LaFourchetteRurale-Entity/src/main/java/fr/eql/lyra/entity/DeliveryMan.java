package fr.eql.lyra.entity;

import java.io.Serializable;

public class DeliveryMan implements Serializable {
    private int deliveryManId;
    private String lastName;
    private String firstName;
    private String password;
    private String email;

    public DeliveryMan(int deliveryManId, String lastName, String firstName, String password, String email) {
        this.deliveryManId = deliveryManId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
    }

    public int getDeliveryManId() {
        return deliveryManId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
