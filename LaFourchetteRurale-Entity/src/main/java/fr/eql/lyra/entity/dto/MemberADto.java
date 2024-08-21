package fr.eql.lyra.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberADto implements Serializable {

    private final long id;
    private final String firstname;
    private final String lastname;
    private final String mail;
//    private final String password;
    private final long phone;
    private final long idgender;
    private final LocalDate birthdate;
    private  final String street;
    private  final String deliveryInformation;
    private  final String billAdress;
    private  final Long creditCard;
    private final LocalDate creditCardDate;
    private final String creditCardMember;
    private final long idDiet;
    private final long idTown;
    private final long idTownBill;
    private final String role;

//    private final String token;

    public MemberADto(long id, String firstname, String lastname, String mail,
                      String password, long phone, long idgender, LocalDate birthdate,
                      String street, String deliveryInformation, String billAdress,
                      Long creditCard, LocalDate creditCardDate, String creditCardMember,
                      long idDiet, long idTown, long idTownBill, String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mail = mail;
//        this.password = password;
        this.phone = phone;
        this.idgender = idgender;
        this.birthdate = birthdate;
        this.street = street;
        this.deliveryInformation = deliveryInformation;
        this.billAdress = billAdress;
        this.creditCard = creditCard;
        this.creditCardDate = creditCardDate;
        this.creditCardMember = creditCardMember;
        this.idDiet = idDiet;
        this.idTown = idTown;
        this.idTownBill = idTownBill;
        this.role = role;
//        this.token = token;
    }

    public long getId() { return id;}
    public String getFirstname() { return firstname;}
    public String getLastname() { return lastname;}
    public String getMail() { return mail;}

//    public String getPassword() { return password;}
    public long getPhone() { return phone;}
    public long getIdgender() { return idgender; }
    public LocalDate getBirthdate() { return birthdate; }
    public String getStreet() { return street; }
    public String getDeliveryInformation() { return deliveryInformation; }
    public String getBillAdress() { return billAdress; }
    public Long getCreditCard() { return creditCard; }
    public String getCreditCardMember() { return creditCardMember; }
    public LocalDate getCreditCardDate() { return creditCardDate; }
    public long getIdDiet() { return idDiet; }
    public long getIdTown() { return idTown; }
    public long getIdTownBill() { return idTownBill; }
    public String getRole() { return role; }

    //    public String getToken() { return token;}
}
