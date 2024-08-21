package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.TourneeBusinessR;
import fr.eql.lyra.dao.TourneeRDao;
import fr.eql.lyra.entity.Tournee;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.List;

@Remote(TourneeBusinessR.class)
@Stateless
public class TourneeBusinessImplR implements TourneeBusinessR {

    @EJB
    TourneeRDao tourneeRDao;


    @Override
    public List<Tournee> findTourneesByDate(LocalDate date) {
        return tourneeRDao.findByDate(date);
    }

}
