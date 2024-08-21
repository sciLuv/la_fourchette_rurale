package fr.eql.lyra.dao;

import fr.eql.lyra.entity.OrderDetail;
import fr.eql.lyra.entity.dto.OrderDetailIdsAndDateDto;

import java.util.List;

public interface OrderDetailDao {
    List<OrderDetail> findAllOrderDetailForAMember(int memberId);
    String putOrderDetailPaymentDate(int[] orderDetailIds);
}
