package fr.eql.lyra.business;

import fr.eql.lyra.entity.Membership;

import java.util.List;

public interface MemberShipBusiness {
    List<Membership> getAllTypeOfMemberShip(int memberId);
}
