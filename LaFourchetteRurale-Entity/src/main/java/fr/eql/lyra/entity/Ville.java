package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Ville implements Serializable {

    private int id;
    private String cityName;
    private int cityCP;
    private LocalDate integDate;
    private LocalDate sortieDate;

    public Ville() {
    }

    public Ville(int id, String cityName, int cityCP, LocalDate integDate, LocalDate sortieDate) {
        this.id = id;
        this.cityName = cityName;
        this.cityCP = cityCP;
        this.integDate = integDate != null ? integDate : LocalDate.now();
        this.sortieDate = sortieDate;
    }
    public Ville(String cityName, int cityCP, LocalDate integDate, LocalDate sortieDate) {
        this.id = -1;
        this.cityName = cityName;
        this.cityCP = cityCP;
        this.integDate = integDate;
        this.sortieDate = sortieDate;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCityCP() {
        return cityCP;
    }

    public LocalDate getIntegDate() {
        return integDate;
    }

    public LocalDate getSortieDate() {
        return sortieDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityCP(int cityCP) {
        this.cityCP = cityCP;
    }

    public void setIntegDate(LocalDate integDate) {
        this.integDate = integDate;
    }

    public void setSortieDate(LocalDate sortieDate) {
        this.sortieDate = sortieDate;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cityCP=" + cityCP +
                ", integDate=" + integDate +
                ", sortieDate=" + sortieDate +
                '}';
    }
}