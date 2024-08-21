package fr.eql.lyra.business;

import fr.eql.lyra.entity.OrderDetail;
import fr.eql.lyra.entity.dto.OrderDetailIdsAndDateDto;

import java.util.List;

public interface OrderDetailBusiness {
    List<OrderDetail> getAllOrderDetailForAMember(int memberId);
    String setPaymentDateForOrderDetails(int[] orderDetailIds);
}
