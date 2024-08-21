package fr.eql.lyra.dao;

import fr.eql.lyra.entity.MembershipType;

import java.util.List;

public interface MembershipTypeDao {
    List<MembershipType> findAllMembershipType();
}
