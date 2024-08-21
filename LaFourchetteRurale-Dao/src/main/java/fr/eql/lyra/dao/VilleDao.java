package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Ville;
import java.util.List;

public interface VilleDao {

    List<Ville> findAllCities();
    Ville findByCityName (String cityName);
    List<Ville> findByCityCP(int cityCP);
    void insertNewVille(Ville ville);
    void disableCity (int cityId);
    List<Ville>sortByCityCPAndName();
    List<Ville> findPackageCities();


}
