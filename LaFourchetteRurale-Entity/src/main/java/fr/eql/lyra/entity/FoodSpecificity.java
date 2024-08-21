package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class FoodSpecificity implements Serializable {
    private final int foodSpecificityId;
    private final String name;
    private final LocalDate startDate;
    private LocalDate endDate;

    //CONSTRUCTOR
    public FoodSpecificity(int foodSpecificityId, String name, LocalDate startDate,
                           LocalDate endDate) {
        this.foodSpecificityId = foodSpecificityId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FoodSpecificity(int foodSpecificityId, String name, LocalDate startDate) {
        this.foodSpecificityId = foodSpecificityId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = null;
    }

    //GETTER
    public int getFoodSpecificityId() {
        return foodSpecificityId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
