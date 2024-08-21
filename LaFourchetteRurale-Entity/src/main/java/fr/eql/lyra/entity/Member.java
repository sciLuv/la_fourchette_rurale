package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable {
        private final int id;
        private final Integer gender;
        private Long diet;
        private Integer town;
        private final String lastname;
        private final String firstname;
        private String mail;
        private Integer phone;
        private final LocalDate birthdate;
        private String street;
        private String deliveryInformation;
        private String billAdress;
        private String password;
        private Long creditCard;
        private LocalDate creditCardDate;
        private String creditCardOwner;


        // CONSTRUCTOR
        public Member(int id, int gender, long diet, int town, String lastname, String firstname, String mail,
                      int phone, LocalDate birthdate, String street, String deliveryInformation, String billAdress,
                      String password, long creditCard, LocalDate creditCardDate, String creditCardOwner) {
                this.id = id;
                this.gender = gender;
                this.diet = diet;
                this.town = town;
                this.lastname = lastname;
                this.firstname = firstname;
                this.mail = mail;
                this.phone = phone;
                this.birthdate = birthdate;
                this.street = street;
                this.deliveryInformation = deliveryInformation;
                this.billAdress = billAdress;
                this.password = password;
                this.creditCard = creditCard;
                this.creditCardDate = creditCardDate;
                this.creditCardOwner = creditCardOwner;
        }
        public Member(int id, String lastname, String firstname, String mail, String password){
                this.id = id;
                this.lastname = lastname;
                this.firstname = firstname;
                this.mail = mail;
                this.password = password;

                this.gender = null;
                this.diet = null;
                this.town = null;
                this.phone = null;
                this.birthdate = null;
                this.street = null;
                this.deliveryInformation = null;
                this.billAdress = null;
                this.creditCard = null;
                this.creditCardDate = null;
                this.creditCardOwner = null;
        }

        public Member(String lastname, String firstname, Integer phone, String street, String deliveryInformation){
                this.id = -1;
                this.lastname = lastname;
                this.firstname = firstname;
                this.mail = null;
                this.password = null;

                this.gender = -1;
                this.diet = null;
                this.town = null;
                this.phone = phone;
                this.birthdate = null;
                this.street = street;
                this.deliveryInformation = deliveryInformation;
                this.billAdress = null;
                this.creditCard = null;
                this.creditCardDate = null;
                this.creditCardOwner = null;
        }

        //GETTER


        public int getId() {
                return id;
        }

        public Integer getGender() {
                return gender;
        }

        public Long getDiet() {
                return diet;
        }

        public void setDiet(Long diet) {
                this.diet = diet;
        }

        public Integer getTown() {
                return town;
        }

        public void setTown(Integer town) {
                this.town = town;
        }

        public String getLastname() {
                return lastname;
        }

        public String getFirstname() {
                return firstname;
        }

        public String getMail() {
                return mail;
        }

        public void setMail(String mail) {
                this.mail = mail;
        }

        public Integer getPhone() {
                return phone;
        }

        public void setPhone(Integer phone) {
                this.phone = phone;
        }

        public LocalDate getBirthdate() {
                return birthdate;
        }

        public String getStreet() {
                return street;
        }

        public void setStreet(String street) {
                this.street = street;
        }

        public String getDeliveryInformation() {
                return deliveryInformation;
        }

        public void setDeliveryInformation(String deliveryInformation) {
                this.deliveryInformation = deliveryInformation;
        }

        public String getBillAdress() {
                return billAdress;
        }

        public void setBillAdress(String billAdress) {
                this.billAdress = billAdress;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Long getCreditCard() {
                return creditCard;
        }

        public void setCreditCard(Long creditCard) {
                this.creditCard = creditCard;
        }

        public LocalDate getCreditCardDate() {
                return creditCardDate;
        }

        public void setCreditCardDate(LocalDate creditCardDate) {
                this.creditCardDate = creditCardDate;
        }

        public String getCreditCardOwner() {
                return creditCardOwner;
        }

        public void setCreditCardOwner(String creditCardOwner) {
                this.creditCardOwner = creditCardOwner;
        }
}
