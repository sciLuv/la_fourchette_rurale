package fr.eql.lyra.entity;

import java.io.Serializable;

public class DeliveryAMan implements Serializable {
    private final int deliveryManId;
    private final String lastName;
    private final String firstName;
    private final String password;
    private String mail;
    private final String role;

    public DeliveryAMan(int deliveryManId, String lastName, String firstName, String password, String mail, String role) {
        this.deliveryManId = deliveryManId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.mail = mail;
        this.role = role;
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

    public String getMail() { return mail; }

    public String getRole() { return role;}
}
