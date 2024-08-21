package fr.eql.lyra.business;

import fr.eql.lyra.entity.Dish;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.MenuOpenSelection;
import fr.eql.lyra.entity.OrderDetail;
import fr.eql.lyra.entity.dto.OrderAndPackageDto;
import fr.eql.lyra.entity.dto.OrderDetailDto;

import java.util.List;

public interface OrderMakingBusiness {
    List<MenuOpenSelection> getMenuOpenToSelection(String date);
    List<Menu> getMenu(int[] menusId);
    List<Dish> getDishesFromMenuId(int[] menusId);
    String postOrder(long memberId, OrderAndPackageDto orderAndPackageDto);
}
