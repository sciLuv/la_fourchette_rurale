package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.AdhDeliveryDateBusiness;
import fr.eql.lyra.business.CityBusiness;
import fr.eql.lyra.dao.AdhDeliveryDateDao;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(AdhDeliveryDateBusiness.class)
@Stateless
public class AdhDeliveryDateBusinessImpl implements AdhDeliveryDateBusiness {

    @EJB
    private AdhDeliveryDateDao adhDeliveryDateDao;

    @Override
    public List<Integer> getDeliveryDateOfMember(int id){
        return adhDeliveryDateDao.findDaysOfDelivery(id);
    }
}
