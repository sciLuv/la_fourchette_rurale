package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Town implements Serializable {
    private final int id;
    private final String name;
    private final String postcode;
    private final LocalDate entry;
    private LocalDate out;


    // CONSTRUCTOR

    public Town(int id, String name, String postcode, LocalDate entry, LocalDate out) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
        this.entry = entry;
        this.out = out;
    }
    public Town() {
        this.id = -1;
        this.name = null;
        this.postcode = null;
        this.entry = null;
        this.out = null;
    }

    //GETTER

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPostcode() {
        return postcode;
    }

    public LocalDate getEntry() {
        return entry;
    }

    public LocalDate getOut() {
        return out;
    }

    //SETTER


}