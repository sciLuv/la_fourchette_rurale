package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Diet implements Serializable {
    private final int dietId;
    private final String name;
    private final LocalDate startDate;
    private LocalDate endDate;

    //CONSTRUCTOR
    public Diet(int dietId, String name, LocalDate startDate,
                           LocalDate endDate) {
        this.dietId = dietId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Diet(int dietId, String name, LocalDate startDate) {
        this.dietId = dietId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = null;
    }

    //GETTER
    public int getDietId() {
        return dietId;
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
