package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.MenuOpeningBusiness;
import fr.eql.lyra.dao.MenuOpenSelectionDao;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.MenuOpenSelection;
import fr.eql.lyra.entity.dto.MenuOpenSelectionDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.List;

@Remote (MenuOpeningBusiness.class)
@Stateless
public class MenuOpeningBusinessImpl implements MenuOpeningBusiness {

    @EJB
    MenuOpenSelectionDao menuOpenSelectionDao;

    @Override
    public List<Menu> findAvailableMenusByDiet(String diet) {
        return menuOpenSelectionDao.findMenusAvailableByDiet(diet);
    }

    @Override
    public void insertMenuOpenSelection(MenuOpenSelectionDto menuOpenSelectionDto) {
        MenuOpenSelection menuOpenSelection = new MenuOpenSelection(
                menuOpenSelectionDto.getIdMenuOpenSelection(),
                menuOpenSelectionDto.getIdTypeRepas(),
                menuOpenSelectionDto.getIdMenu(),
                menuOpenSelectionDto.getSelectionOpening(),
                menuOpenSelectionDto.getSelectingClosing(),
                menuOpenSelectionDto.getIsDefault(),
                menuOpenSelectionDto.getDayPosition()
        );
        menuOpenSelectionDao.insertMenuOpenSelection(menuOpenSelection);
    }

    @Override
    public LocalDate getLastEndSelectionDate() {
        return menuOpenSelectionDao.getLastEndSelectionDate();
    }
}
