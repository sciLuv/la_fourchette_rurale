package fr.eql.lyra.entity;

import java.io.Serializable;

public class Gender implements Serializable {
    private final int id;
    private final String genderName;

    //CONSTRUCTOR
    public Gender(int id, String genderName) {
        this.id = id;
        this.genderName = genderName;
    }
    //GETTER

    public int getId() {
        return id;
    }

    public String getGenderName() {
        return genderName;
    }
}
