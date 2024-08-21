package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.DishTypeBusiness;
import fr.eql.lyra.business.OrderDetailBusiness;
import fr.eql.lyra.dao.OrderDetailDao;
import fr.eql.lyra.entity.DishType;
import fr.eql.lyra.entity.OrderDetail;
import fr.eql.lyra.entity.dto.OrderDetailIdsAndDateDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(OrderDetailBusiness.class)
@Stateless
public class OrderDetailBusinessImpl implements OrderDetailBusiness {
    @EJB
    OrderDetailDao orderDetailDao;

    @Override
    public List<OrderDetail> getAllOrderDetailForAMember(int memberId) {
        return orderDetailDao.findAllOrderDetailForAMember(memberId);
    }

    @Override
    public String setPaymentDateForOrderDetails(int[] orderDetailIds){
        return orderDetailDao.putOrderDetailPaymentDate(orderDetailIds);
    };
}
