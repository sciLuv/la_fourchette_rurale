package fr.eql.lyra.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderDetailDto implements Serializable {

    private Integer dishId1;
    private Integer dishId2;
    private Integer dishId3;
    private LocalDate choiceDate;
    private LocalDate choiceValidationDate;
    private LocalDate actualMealDate;
    private LocalDate billDate;

    public Integer getDishId1() {
        return dishId1;
    }

    public void setDishId1(Integer dishId1) {
        this.dishId1 = dishId1;
    }

    public Integer getDishId2() {
        return dishId2;
    }

    public void setDishId2(Integer dishId2) {
        this.dishId2 = dishId2;
    }

    public Integer getDishId3() {
        return dishId3;
    }

    public void setDishId3(Integer dishId3) {
        this.dishId3 = dishId3;
    }

    public LocalDate getChoiceDate() {
        return choiceDate;
    }

    public void setChoiceDate(LocalDate choiceDate) {
        this.choiceDate = choiceDate;
    }

    public LocalDate getChoiceValidationDate() {
        return choiceValidationDate;
    }

    public void setChoiceValidationDate(LocalDate choiceValidationDate) {
        this.choiceValidationDate = choiceValidationDate;
    }

    public LocalDate getActualMealDate() {
        return actualMealDate;
    }

    public void setActualMealDate(LocalDate actualMealDate) {
        this.actualMealDate = actualMealDate;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
}
