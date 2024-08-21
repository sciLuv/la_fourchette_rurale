package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Membership;

import java.util.List;

public interface MemberShipDao {
    List<Membership> findAllMembershipForAMember(int memberId);
}
