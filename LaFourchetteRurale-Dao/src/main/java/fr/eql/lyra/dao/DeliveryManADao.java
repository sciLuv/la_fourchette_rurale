package fr.eql.lyra.dao;

import fr.eql.lyra.entity.DeliveryAMan;

import java.util.List;

public interface DeliveryManADao {

    DeliveryAMan authenticateDeliveryMan(String username, String password);
//
////    Session findSession(String token);
////    void updateSession(String token, long memberId);
////    Role findRoleByIdMember(long id);
    List<DeliveryAMan> findAllButSelfDeliveryMan(int deliveryManId);
}
