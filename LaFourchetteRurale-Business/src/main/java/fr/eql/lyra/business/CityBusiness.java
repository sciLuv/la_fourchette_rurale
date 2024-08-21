package fr.eql.lyra.business;

import fr.eql.lyra.entity.Ville;

import java.util.List;

public interface CityBusiness {

    List<Ville> getAllCities();

    Ville findCityByCityName(String cityName);

    List<Ville> findCityByCityCP(int cityCP);

    void addNewCity(Ville ville);

    void disableCity(int id);

    List<Ville> findPackageCities();

}
