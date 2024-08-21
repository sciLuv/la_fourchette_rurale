package fr.eql.lyra.entity.dto;

import java.io.Serializable;

public class DeliveryManDto implements Serializable {

    private String lastName;
    private String firstName;
    private String email;

    public DeliveryManDto(String lastName, String firstName, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
