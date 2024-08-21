package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.TourneeBusiness;
import fr.eql.lyra.dao.TourneeDao;
import fr.eql.lyra.dao.VilleDao;
import fr.eql.lyra.entity.Tournee;
import fr.eql.lyra.entity.TownRound;
import fr.eql.lyra.entity.Ville;
import fr.eql.lyra.entity.dto.TourneeDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Remote(TourneeBusiness.class)
@Stateless
public class TourneeBusinessImpl implements TourneeBusiness {

    @EJB
    TourneeDao tourneeDao;

    @EJB
    VilleDao villeDao;

    public TourneeBusinessImpl() {
    }

    public TourneeBusinessImpl(TourneeDao tourneeDao, VilleDao villeDao) {
        this.tourneeDao = tourneeDao;
        this.villeDao = villeDao;
    }

    @Override
    public Tournee findTourneeById(int id) {
        return tourneeDao.findById(id);
    }

    @Override
    public List<Tournee> findTourneesByDate(LocalDate date) {
        return tourneeDao.findByDate(date);
    }

    @Override
    public List<Tournee> findTourneesByVille(Ville ville) {
        return tourneeDao.findByVille(ville);
    }

    @Override
    public void addTournee(Tournee tournee) {
        tourneeDao.addTournee(tournee);

    }

    @Override
    public void updateTournee(Tournee tournee) {
        tourneeDao.update(tournee,null);

    }

    @Override
    public void deleteTournee(int id) {
        tourneeDao.delete(id);

    }

    @Override
    public String addTournee(TourneeDto[] tournees) {
        List<Tournee> tourneesList = new ArrayList<>();
        for (TourneeDto tournee : tournees) {
            tourneesList.add(
                    new Tournee(
                           -1,
                            tournee.getDeliveryMan(),
                            tournee.getDate(),
                            null,
                            null
                    )
            );
        }
        List<int[]> distribuerList = new ArrayList<>();
        for (TourneeDto tournee : tournees) {
            distribuerList.add(tournee.getCities());
        }

        for (Tournee tournee : tourneesList) {
            System.out.println(tournee.getDeliveryManId());
        }
        for (int[] ints : distribuerList) {
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        }
        return tourneeDao.addingNew(tourneesList, distribuerList);
    }

    @Override
    public List<Tournee> getTourneesByDeliveryManId(int deliveryManId) {
        System.out.println("test Business 1");
        return tourneeDao.findByIdDeliveryMan(deliveryManId);
    }

    @Override
    public List<Integer> getTownByTournee(int tourneeId) {
        return tourneeDao.findTownByTournee(tourneeId);
    }


}
