package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDetail implements Serializable {
    private final int orderDetailId;
    private final int packageId;
    private final int orderId;
    private final Integer dishId1;
    private final Integer dishId2;
    private final Integer dishId3;
    private final Integer selectedMenuId;
    private final LocalDate choiceDate;
    private final LocalDate choiceValidationDate;
    private LocalDate actualMealDate;
    private LocalDate paymentDate;
    private LocalDate billDate;

    //CONSTRUCTOR
    public OrderDetail(int orderDetailId, int packageId, int orderId, Integer dishId1,Integer dishId2, Integer dishId3,
                       Integer selectedMenuId, LocalDate choiceDate, LocalDate choiceValidationDate,
                       LocalDate actualMealDate, LocalDate paymentDate, LocalDate billDate) {
        this.orderDetailId = orderDetailId;
        this.packageId = packageId;
        this.orderId = orderId;
        this.dishId1 = dishId1;
        this.dishId2 = dishId2;
        this.dishId3 = dishId3;
        this.selectedMenuId = selectedMenuId;
        this.choiceDate = choiceDate;
        this.choiceValidationDate = choiceValidationDate;
        this.actualMealDate = actualMealDate;
        this.paymentDate = paymentDate;
        this.billDate = billDate;
    }

    //GETTER
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public int getPackageId() {
        return packageId;
    }

    public int getOrderId() {
        return orderId;
    }

    public Integer getDishId1() {
        return dishId1;
    }
    public int getDishId2() {
        return dishId2;
    }
    public int getDishId3() {
        return dishId3;
    }
    public Integer getSelectedMenuId() {
        return selectedMenuId;
    }
    public LocalDate getChoiceDate() {
        return choiceDate;
    }

    public LocalDate getChoiceValidationDate() {
        return choiceValidationDate;
    }

    public LocalDate getActualMealDate() {
        return actualMealDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalDate getBillDate() {
        return billDate;
    }
}
