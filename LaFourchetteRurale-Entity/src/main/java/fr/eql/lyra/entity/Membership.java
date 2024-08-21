package fr.eql.lyra.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDate;

public class Membership implements Serializable {
    private final int membershipId;
    private final int membershipTypeId;
    private final int memberId;
    private final LocalDate startDate;
    private final LocalDate expectedEndDate;
    private LocalDate actualEndDate;
    private LocalDate paymentDate;
    private LocalDate billDate;

    //CONSTRUCTOR
    public Membership(int membershipId, int membershipTypeId, int memberId, LocalDate startDate,
                      LocalDate expectedEndDate, LocalDate actualEndDate, LocalDate paymentDate,
                      LocalDate billDate) {
        this.membershipId = membershipId;
        this.membershipTypeId = membershipTypeId;
        this.memberId = memberId;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.actualEndDate = actualEndDate;
        this.paymentDate = paymentDate;
        this.billDate = billDate;
    }

    //GETTER

    public int getMembershipId() {
        return membershipId;
    }

    public int getMembershipTypeId() {
        return membershipTypeId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalDate getBillDate() {
        return billDate;
    }


}
