package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Day;

import java.util.List;

public interface DayDao {

    List<Day> findAllDays();
}
