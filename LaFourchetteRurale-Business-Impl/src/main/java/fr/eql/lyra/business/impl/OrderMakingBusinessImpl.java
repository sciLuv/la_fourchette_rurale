package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.OrderMakingBusiness;
import fr.eql.lyra.dao.OrderDao;
import fr.eql.lyra.entity.*;
import fr.eql.lyra.entity.Package;
import fr.eql.lyra.entity.dto.OrderAndPackageDto;
import fr.eql.lyra.entity.dto.OrderDetailDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Remote(OrderMakingBusiness.class)
@Stateless
public class OrderMakingBusinessImpl implements OrderMakingBusiness {


    @EJB
    private OrderDao orderDao;



    public List<MenuOpenSelection> getMenuOpenToSelection(String date){ return orderDao.findMenuOpenToSelection(date);}
    public List<Menu> getMenu(int[] menusId){
        return orderDao.findMenuFromMenuOpenToSelection(menusId);
    }
    public List<Dish> getDishesFromMenuId(int[] menusId){
        return orderDao.findDishFromMenu(menusId);
    }
    public String postOrder(long memberId, OrderAndPackageDto orderAndPackageDto){
        List<OrderDetail> od = new ArrayList<>();
        Integer orderDetailCount = 0;
        for (OrderDetailDto orderDetailDto : orderAndPackageDto.getSelectionMeals()) {
            System.out.println("-------");
            System.out.println(orderDetailDto.getChoiceDate());
            System.out.println("-------");
            od.add(
                    new OrderDetail(
                            orderDetailCount, -1, -1,
                            orderDetailDto.getDishId1(), orderDetailDto.getDishId2(), orderDetailDto.getDishId3(),
                            1,
                            orderDetailDto.getChoiceDate(), orderDetailDto.getChoiceValidationDate(), orderDetailDto.getActualMealDate(),
                            null, orderDetailDto.getBillDate()
                    )
            );
            System.out.println(orderDetailDto.getBillDate());
            System.out.println("orderDetailCount before : " + orderDetailCount);
            orderDetailCount++;
            System.out.println("orderDetailCount after : " + orderDetailCount);
        }
        Iterator<OrderDetail> iterator = od.iterator();
        while (iterator.hasNext()) {
            OrderDetail orderDetail = iterator.next();
            if (orderDetail.getChoiceDate() == null) {
                iterator.remove();
            }
        }

        for (OrderDetail orderDetail : od) {
            System.out.println(orderDetail.getChoiceValidationDate() + " " + orderDetail.getActualMealDate() + " " + orderDetail.getDishId1());
        }
        List<Package> packages = new ArrayList<>();
        for (LocalDate deliveryDate : orderAndPackageDto.getDeliveryDates()) {
            System.out.println("________________");
            System.out.println(deliveryDate);
            packages.add(
                    new Package(0,null,0, deliveryDate,null, null)
            );
        }

        for (Package deliveryDate : packages) {
            System.out.println("______---------");
            System.out.println(deliveryDate.getExpectedDelivery());
        }

        System.out.println("packageSize :" + packages.size());
        List<List<Integer>> association = new ArrayList<>();
        for (int j = 0; j < packages.size(); j++) {
            List<Integer> associationConstruction = new ArrayList<>();
            for (int i = 0; i < od.size(); i++) {
            System.out.println("orderDetailId : " + od.get(i).getOrderDetailId());
                LocalDate mealDate = od.get(i).getActualMealDate();
                LocalDate mealChoiceDate = od.get(i).getChoiceDate();
                LocalDate deliveryDate = packages.get(j).getExpectedDelivery();
                LocalDate nextDeliveryDate;
                if(j+1 < packages.size()) nextDeliveryDate = packages.get(j+1).getExpectedDelivery();
                else nextDeliveryDate = null;
                if(mealChoiceDate != null){
                    if (mealDate.equals(deliveryDate)){
                        associationConstruction.add(od.get(i).getOrderDetailId());
                    }
                    if((mealDate.isAfter(deliveryDate)) && ((nextDeliveryDate == null)||mealDate.isBefore(nextDeliveryDate))){
                        associationConstruction.add(od.get(i).getOrderDetailId());
                    }
                }
            }
            association.add(associationConstruction);
        }

//        for (List<Integer> integers : association) {
//            System.out.println("début id livraison");
//            for (Integer integer : integers) {
//                System.out.println(integer);
//            }
//            System.out.println("fin id livraison");
//        }

        for (int i = 0; i < association.size(); i++) {
            List<Integer> integers = association.get(i);
            if(association.get(i).isEmpty()){
                System.out.println("c'est vide!!!!!!");
                packages.remove(i);
            }
            System.out.println("début id livraison");

            for (int j = 0; j < integers.size(); j++) {
                Integer integer = integers.get(j);
                System.out.println(integer);
            }

            System.out.println("fin id livraison");
        }

        String response = orderDao.addOrderAndOrderDetail(memberId, od, packages, association );
        return response;
    };

}
