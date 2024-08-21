package fr.eql.lyra.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDetailIdsAndDateDto implements Serializable {
    private int[] orderDetailIds;
    private LocalDate paymentDate;

    public int[] getOrderDetailIds() {
        return orderDetailIds;
    }

    public void setOrderDetailIds(int[] orderDetailIds) {
        this.orderDetailIds = orderDetailIds;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
