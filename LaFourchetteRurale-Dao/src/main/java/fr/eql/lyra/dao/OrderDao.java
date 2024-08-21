package fr.eql.lyra.dao;

import fr.eql.lyra.entity.*;
import fr.eql.lyra.entity.Package;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {
    List<MenuOpenSelection> findMenuOpenToSelection(String date);
    List<Menu> findMenuFromMenuOpenToSelection(int[] menusId);
    List<Dish> findDishFromMenu(int[] menusId);
    String addOrderAndOrderDetail(long memberId, List<OrderDetail> orderDetails, List<Package> packages, List<List<Integer>> association );
}
