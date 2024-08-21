package fr.eql.lyra.entity.dto;

import java.io.Serializable;

public class DeliveryinformationDto implements Serializable {
    String firstname;
    String lastName;
    String street;
    String town;
    long idPackage;
    long idAdh;

    public DeliveryinformationDto(String firstname, String lastName, String street, String town, long idPackage, long idAdh) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.street = street;
        this.town = town;
        this.idPackage = idPackage;
        this.idAdh = idAdh;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public long getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(long idPackage) {
        this.idPackage = idPackage;
    }

    public long getIdAdh() {
        return idAdh;
    }

    public void setIdAdh(long idAdh) {
        this.idAdh = idAdh;
    }
}
