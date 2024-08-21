package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class MembershipType implements Serializable {
    private final long membershipTypeId;
    private long parentMembershipTypeId;
    private final String name;
    private final long duration;
    private final long price;
    private LocalDate validityEndDate;

    public MembershipType(long membershipTypeId, long parentMembershipTypeId, String name, long duration, long price, LocalDate validityEndDate) {
        this.membershipTypeId = membershipTypeId;
        this.parentMembershipTypeId = parentMembershipTypeId;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.validityEndDate = validityEndDate;
    }

    public long getMembershipTypeId() {
        return membershipTypeId;
    }

    public long getParentMembershipTypeId() {
        return parentMembershipTypeId;
    }

    public void setParentMembershipTypeId(long parentMembershipTypeId) {
        this.parentMembershipTypeId = parentMembershipTypeId;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return duration;
    }

    public long getPrice() {
        return price;
    }

    public LocalDate getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(LocalDate validityEndDate) {
        this.validityEndDate = validityEndDate;
    }
// You may also include setters if needed
}
