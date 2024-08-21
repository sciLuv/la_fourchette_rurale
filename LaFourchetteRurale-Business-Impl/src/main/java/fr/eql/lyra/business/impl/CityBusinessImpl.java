package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.CityBusiness;
import fr.eql.lyra.dao.VilleDao;
import fr.eql.lyra.entity.Ville;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(CityBusiness.class)
@Stateless
public class CityBusinessImpl implements CityBusiness {

    @EJB
    private VilleDao villeDao;

    public CityBusinessImpl() {
    }

    public CityBusinessImpl(VilleDao villeDao) {
        this.villeDao = villeDao;
    }

    @Override
    public List<Ville> getAllCities() {
        return villeDao.findAllCities();
    }

    @Override
    public Ville findCityByCityName(String cityName) {
        return villeDao.findByCityName(cityName);
    }

    @Override
    public List<Ville> findCityByCityCP(int cityCP) {
        System.out.println("toto");
        return villeDao.findByCityCP(cityCP);
    }

    @Override
    public void addNewCity(Ville ville) {
        villeDao.insertNewVille(ville);

    }

    @Override
    public void disableCity(int id) {
        villeDao.disableCity(id);
    }

    @Override
    public List<Ville> findPackageCities() {
        return villeDao.findPackageCities();
    }

}
