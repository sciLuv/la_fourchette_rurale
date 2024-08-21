package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.DishTypeBusiness;
import fr.eql.lyra.business.FoodSpecificityBusiness;
import fr.eql.lyra.dao.FoodSpecificityDao;
import fr.eql.lyra.entity.FoodSpecificity;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(FoodSpecificityBusiness.class)
@Stateless
public class FoodSpecificityBusinessImpl implements FoodSpecificityBusiness {

    @EJB
    FoodSpecificityDao foodSpecificityDao;

    @Override
    public List<FoodSpecificity> getAllFoodSpecificity(){
        return foodSpecificityDao.getAllFoodSpecificity();
    };
}
