package fr.eql.lyra.dao;

import fr.eql.lyra.entity.DeliveryMan;
import fr.eql.lyra.entity.Session;
import fr.eql.lyra.entity.Ville;

import java.util.List;

public interface DeliveryManDao {

    //验证登录信息，接收邮箱和密码作为参数
    DeliveryMan authenticate(String email, String password);
    //重置密码
    void updatePassword(String email, String newPassword);

    List<DeliveryMan> findAllDeliveryMans();



}
