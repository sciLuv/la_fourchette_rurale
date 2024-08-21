package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Diet;

import java.util.List;

public interface DietDao {

    List<Diet> findAllActiveDiet();
}
