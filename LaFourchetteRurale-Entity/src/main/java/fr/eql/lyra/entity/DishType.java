package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class DishType implements Serializable {
        private int dishTypeId;
        private Integer parentDishTypeId;
        private String dishTypeName;
        private int price;
        private LocalDate withdrawalDate;

        public DishType(int dishTypeId, Integer parentDishTypeId, String dishTypeName, int price,
                        LocalDate withdrawalDate) {
            this.dishTypeId = dishTypeId;
            this.parentDishTypeId = parentDishTypeId;
            this.dishTypeName = dishTypeName;
            this.price = price;
            this.withdrawalDate = withdrawalDate;
        }

    public DishType(int dishTypeId, Integer parentDishTypeId, String dishTypeName, int price) {
        this.dishTypeId = dishTypeId;
        this.parentDishTypeId = parentDishTypeId;
        this.dishTypeName = dishTypeName;
        this.price = price;
        this.withdrawalDate = null;
    }

    public int getDishTypeId() {
            return dishTypeId;
        }

        public Integer getParentDishTypeId() {
            return parentDishTypeId;
        }

        public String getDishTypeName() {
            return dishTypeName;
        }

        public int getPrice() {
            return price;
        }

        public LocalDate getWithdrawalDate() {
            return withdrawalDate;
        }
    }

