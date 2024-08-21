package fr.eql.lyra.entity.dto;

import fr.eql.lyra.entity.Ville;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TourneeDto implements Serializable {

    private int deliveryMan;
    private int[] cities;
    private LocalDate date;

    public int getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(int deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public int[] getCities() {
        return cities;
    }

    public void setCities(int[] cities) {
        this.cities = cities;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
