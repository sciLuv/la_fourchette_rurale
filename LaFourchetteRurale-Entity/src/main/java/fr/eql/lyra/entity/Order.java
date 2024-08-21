package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Order implements Serializable {
    private final int orderId;
    private final Integer memberId;
    private final Integer menuId;
    private final LocalDate validationDate;
    private LocalDate cancellationDate;

    //CONSTRUCTOR
    public Order(int orderId, Integer memberId, Integer menuId,
                 LocalDate validationDate, LocalDate cancellationDate) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.menuId = menuId;
        this.validationDate = validationDate;
        this.cancellationDate = cancellationDate;
    }

    public Order(int orderId, Integer memberId, Integer menuId,
                 LocalDate validationDate) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.menuId = menuId;
        this.validationDate = validationDate;
        this.cancellationDate = null;
    }

    //GETTER
    public int getOrderId() {
        return orderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public LocalDate getValidationDate() {
        return validationDate;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

}
