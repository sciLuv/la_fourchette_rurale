package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.DayBusiness;
import fr.eql.lyra.dao.DayDao;
import fr.eql.lyra.entity.Day;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(DayBusiness.class)
@Stateless
public class DayBusinessImpl implements DayBusiness{

    @EJB
    private DayDao dayDao;
    public List<Day> getAllDays(){ return dayDao.findAllDays();}
}
