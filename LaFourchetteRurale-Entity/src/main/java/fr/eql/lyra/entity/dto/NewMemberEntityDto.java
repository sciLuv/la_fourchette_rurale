package fr.eql.lyra.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class NewMemberEntityDto implements Serializable {
    private String billAdress;
    private LocalDate birthdate;
    private LocalDate dateCarte;
    private int[] deliveryDays;
    private String deliveryInformation;
    private long diet;
    private long gender;
    private long id;
    private long membershipType;
    private long numCarte;
    private long phone;
    private String porteurCarte;
    private long selectedFood;
    private String street;
    private long town;
    private LocalDate membershipBegin;
    private LocalDate membershipEnd;

    public String getBillAdress() {
        return billAdress;
    }

    public void setBillAdress(String billAdress) {
        this.billAdress = billAdress;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getDateCarte() {
        return dateCarte;
    }

    public void setDateCarte(LocalDate dateCarte) {
        this.dateCarte = dateCarte;
    }

    public int[] getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int[] deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public String getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(String deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public long getDiet() {
        return diet;
    }

    public void setDiet(long diet) {
        this.diet = diet;
    }

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(long membershipType) {
        this.membershipType = membershipType;
    }

    public long getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(long numCarte) {
        this.numCarte = numCarte;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPorteurCarte() {
        return porteurCarte;
    }

    public void setPorteurCarte(String porteurCarte) {
        this.porteurCarte = porteurCarte;
    }

    public long getSelectedFood() {
        return selectedFood;
    }

    public void setSelectedFood(long selectedFood) {
        this.selectedFood = selectedFood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getTown() {
        return town;
    }

    public void setTown(long town) {
        this.town = town;
    }

    public LocalDate getMembershipBegin() {
        return membershipBegin;
    }

    public void setMembershipBegin(LocalDate membershipBegin) {
        this.membershipBegin = membershipBegin;
    }

    public LocalDate getMembershipEnd() {
        return membershipEnd;
    }

    public void setMembershipEnd(LocalDate membershipEnd) {
        this.membershipEnd = membershipEnd;
    }
}