package fr.eql.lyra.dao;

import java.util.List;

public interface AdhDeliveryDateDao {
    List<Integer> findDaysOfDelivery(int id);
}
