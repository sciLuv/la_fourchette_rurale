package fr.eql.lyra.business;

import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.dto.MenuOpenSelectionDto;

import java.time.LocalDate;
import java.util.List;

public interface MenuOpeningBusiness {

    List<Menu> findAvailableMenusByDiet (String diet);
    void insertMenuOpenSelection (MenuOpenSelectionDto menuOpenSelectionDto);
    LocalDate getLastEndSelectionDate();
}
