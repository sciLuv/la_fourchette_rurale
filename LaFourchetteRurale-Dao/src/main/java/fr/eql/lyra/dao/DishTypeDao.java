package fr.eql.lyra.dao;

import fr.eql.lyra.entity.DishType;

import java.util.List;

public interface DishTypeDao {
    List<DishType> sendAllActiveDishType();
}
