package fr.eql.lyra.business;

import fr.eql.lyra.entity.Tournee;
import fr.eql.lyra.entity.Ville;
import fr.eql.lyra.entity.dto.TourneeDto;

import java.time.LocalDate;
import java.util.List;

public interface TourneeBusiness {

    Tournee findTourneeById(int id);

    List<Tournee> findTourneesByDate(LocalDate date);

    List<Tournee> findTourneesByVille(Ville ville);

    void addTournee(Tournee tournee);

    void updateTournee(Tournee tournee);

    void deleteTournee(int id);

    String addTournee(TourneeDto[] tournees);

    List<Tournee> getTourneesByDeliveryManId(int deliveryManId);

    List<Integer> getTownByTournee(int tourneeId);



}
