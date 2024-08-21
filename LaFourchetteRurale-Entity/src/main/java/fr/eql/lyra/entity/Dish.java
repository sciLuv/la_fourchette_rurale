package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Dish implements Serializable {
    private final int dishId;
    private final long dietaryRegimeId;
    private final int dishTypeId;
    private String dishName;
    private String description;
    private String imageUrl;
    private final LocalDate entryDate;
    private LocalDate withdrawalDate;

    //CONSTRUCTOR
    public Dish(int dishId, long dietaryRegimeId, int dishTypeId, String dishName,
                String description, String imageUrl, LocalDate entryDate,
                LocalDate withdrawalDate) {
        this.dishId = dishId;
        this.dietaryRegimeId = dietaryRegimeId;
        this.dishTypeId = dishTypeId;
        this.dishName = dishName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.entryDate = entryDate;
        this.withdrawalDate = withdrawalDate;
    }

    public Dish(int dishId, long dietaryRegimeId, int dishTypeId, LocalDate entryDate) {
        this.dishId = dishId;
        this.dietaryRegimeId = dietaryRegimeId;
        this.dishTypeId = dishTypeId;
        this.entryDate = entryDate;
    }

    //GETTER
    public int getDishId() {
        return dishId;
    }

    public long getDietaryRegimeId() {
        return dietaryRegimeId;
    }

    public int getDishTypeId() {
        return dishTypeId;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

}
