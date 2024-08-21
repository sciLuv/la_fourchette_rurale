package fr.eql.lyra.dao;

import fr.eql.lyra.entity.MemberA;

import java.util.List;

public interface MemberADao {

    MemberA authenticate(String username, String password);

//    Session findSession(String token);
//    void updateSession(String token, long memberId);
//    Role findRoleByIdMember(long id);
    List<MemberA> findAllButSelf(long id);
}
