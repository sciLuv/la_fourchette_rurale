package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.MenuOpenSelection;

import java.time.LocalDate;
import java.util.List;

public interface MenuOpenSelectionDao {

    List<Menu> findMenusAvailableByDiet(String diet);
    void insertMenuOpenSelection (MenuOpenSelection menuOpenSelection);
    LocalDate getLastEndSelectionDate();

}
