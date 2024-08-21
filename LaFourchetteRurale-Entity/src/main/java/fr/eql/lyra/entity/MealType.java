package fr.eql.lyra.entity;

import java.io.Serializable;

public class MealType implements Serializable {
    private final int mealTypeId;
    private final char mealLabel;

    //CONSTRUCTOR
    public MealType(int mealTypeId, char mealLabel) {
        this.mealTypeId = mealTypeId;
        this.mealLabel = mealLabel;
    }

    //GETTER
    public int getMealTypeId() {
        return mealTypeId;
    }

    public char getMealLabel() {
        return mealLabel;
    }
}
