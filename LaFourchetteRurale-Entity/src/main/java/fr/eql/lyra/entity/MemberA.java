package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberA implements Serializable {
        private final Long id;
//        private final Integer gender;
//        private Long diet;
//        private Integer town;
        private final String lastname;
        private final String firstname;
//        private final String username;
        private final String password;
        private String mail;
        private Long phone;
        private Long idgender;
        private LocalDate birthdate;
        private String street;
        private String deliveryInformation;
        private String billAdress;
        private Long creditCard;
        private LocalDate creditCardDate;
        private String creditCardMember;
        private final Long idDiet;
        private final Long idTown;
        private final Long idTownBill;
        private final String role;



        // CONSTRUCTOR
        public MemberA(long id, String lastname, String firstname, String mail, String password, long phone, long idgender, String street, String deliveryInformation, String billAdress, long creditCard, LocalDate creditCardDate, String creditCardMember, LocalDate birthdate, long idDiet, long idTown, long idTownBill, String role) {
                this.id = id;
//                this.diet = diet;
//                this.town = town;
                this.lastname = lastname;
                this.firstname = firstname;
//                this.username = username;
                this.password = password;
                this.mail = mail;
                this.phone = phone;
                this.idgender = idgender;
                this.street = street;
                this.deliveryInformation = deliveryInformation;
                this.billAdress = billAdress;
                this.creditCard = creditCard;
                this.creditCardDate = creditCardDate;
                this.creditCardMember = creditCardMember;
                this.birthdate = birthdate;
                this.idDiet = idDiet;
                this.idTown = idTown;
                this.idTownBill = idTownBill;
                this.role = role;
        }

        //GETTER
        public Long getIdgender() {
                return idgender;
        }

        public Long getId() {
                return id;
        }

//        public Integer getGender() {
//                return gender;
//        }

//        public Long getDiet() {
//                return diet;
//        }

//        public Integer getTown() {
//                return town;
//        }

        public String getLastname() {
                return lastname;
        }

        public String getFirstname() {
                return firstname;
        }

        public String getMail() {
                return mail;
        }

        public Long getPhone() {
                return phone;
        }

        public LocalDate getBirthdate() { return birthdate; }

        public String getStreet() {
                return street;
        }

        public String getDeliveryInformation() {
                return deliveryInformation;
        }

        public String getBillAdress() {
                return billAdress;
        }

        public String getPassword() {
                return password;
        }

        public Long getCreditCard() {
                return creditCard;
        }

        public LocalDate getCreditCardDate() {
                return creditCardDate;
        }

        public String getCreditCardMember() {
                return creditCardMember;
        }

        public Long getIdDiet() { return idDiet; }

        public Long getIdTown() { return idTown; }

        public Long getIdTownBill() { return idTownBill; }

        public String getRole() { return role; }
}
