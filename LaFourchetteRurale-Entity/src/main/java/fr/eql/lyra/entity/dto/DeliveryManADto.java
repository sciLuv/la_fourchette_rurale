package fr.eql.lyra.entity.dto;

import java.io.Serializable;

public class DeliveryManADto implements Serializable {

    private int deliveryManId;
    private String lastName;
    private String firstName;
    private String password;
    private String mail;
    private final String role;

//    private final String token;

    public DeliveryManADto(int deliveryManId, String lastName, String firstName, String password, String mail, String role) {
        this.deliveryManId = deliveryManId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.mail = mail;
        this.role = role;
//        this.token = token;
    }

    public int getDeliveryManId() { return deliveryManId; }

    public String getLastName() { return lastName; }

    public String getFirstName() { return firstName; }

    public String getPassword() { return password; }

    public String getMail() { return mail; }

    public String getRole() { return role; }

    //    public String getToken() { return token;}
}
