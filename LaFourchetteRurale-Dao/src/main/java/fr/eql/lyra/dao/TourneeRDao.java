package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Tournee;

import java.time.LocalDate;
import java.util.List;

public interface TourneeRDao {
    List<Tournee> findByDate(LocalDate date);
}
