package fr.eql.lyra.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OrderAndPackageDto implements Serializable {
    OrderDetailDto[] selectionMeals;
    LocalDate[] deliveryDates;

    public OrderDetailDto[] getSelectionMeals() {
        return selectionMeals;
    }

    public void setSelectionMeals(OrderDetailDto[] selectionMeals) {
        this.selectionMeals = selectionMeals;
    }

    public LocalDate[] getDeliveryDates() {
        return deliveryDates;
    }

    public void setDeliveryDates(LocalDate[] deliveryDates) {
        this.deliveryDates = deliveryDates;
    }
}
