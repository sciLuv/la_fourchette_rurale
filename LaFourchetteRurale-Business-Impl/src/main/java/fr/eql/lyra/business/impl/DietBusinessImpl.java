package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.DietBusiness;
import fr.eql.lyra.business.OrderMakingBusiness;
import fr.eql.lyra.dao.DietDao;
import fr.eql.lyra.entity.Diet;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(DietBusiness.class)
@Stateless
public class DietBusinessImpl implements DietBusiness {

    @EJB
    private DietDao dietDao;
    public List<Diet> getAllActiveDiet(){ return dietDao.findAllActiveDiet();}
}
