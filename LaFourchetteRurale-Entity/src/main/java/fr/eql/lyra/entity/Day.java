package fr.eql.lyra.entity;

import java.io.Serializable;

public class Day implements Serializable {
    private final int id;
    private final String nameOfDay;

    //CONSTRUCTOR
    public Day(int id, String nameOfDay) {
        this.id = id;
        this.nameOfDay = nameOfDay;
    }

    //GETTER
    public int getId() {
        return id;
    }

    public String getNameOfDay() {
        return nameOfDay;
    }
}
