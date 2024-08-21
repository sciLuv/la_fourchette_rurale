package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Tournee;
import fr.eql.lyra.entity.TownRound;
import fr.eql.lyra.entity.Ville;
import java.time.LocalDate;
import java.util.List;

public interface TourneeDao {

    Tournee findById(int id);
    List<Tournee> findByDate(LocalDate date);
    List<Tournee> findByVille(Ville ville);
    void addTournee(Tournee tournee);
    void update(Tournee tournee, List<Ville> villes);
    void delete(int id);
    String addingNew(List<Tournee> tourneesList, List<int[]> distribuerList);
    //根据配送员id查找id_tournee
    List<Tournee> findByIdDeliveryMan (int deliveryManId);
    List<Integer> findTownByTournee (int tourneeId);

}
