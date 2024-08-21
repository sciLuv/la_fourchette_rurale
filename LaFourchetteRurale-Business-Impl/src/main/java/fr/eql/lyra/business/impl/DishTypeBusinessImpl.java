package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.DishTypeBusiness;
import fr.eql.lyra.dao.DishTypeDao;
import fr.eql.lyra.entity.DishType;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(DishTypeBusiness.class)
@Stateless
public class DishTypeBusinessImpl implements DishTypeBusiness {

    @EJB
    DishTypeDao dishTypeDao;
    @Override
    public List<DishType> getAllActiveDishType() {
        return dishTypeDao.sendAllActiveDishType();
    }
}
