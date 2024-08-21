package fr.eql.lyra.business;

import fr.eql.lyra.entity.Tournee;

import java.time.LocalDate;
import java.util.List;

public interface TourneeBusinessR {
    List<Tournee> findTourneesByDate(LocalDate date);
}
