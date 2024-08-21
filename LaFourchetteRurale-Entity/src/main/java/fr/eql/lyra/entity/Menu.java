package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Menu implements Serializable {
    private final int menuId;
    private final String name;
    private final String dietType;
    private final LocalDate creationDate;
    private LocalDate withdrawalDate;

    public Menu(int menuId, String name, String dietType, LocalDate creationDate,
                LocalDate withdrawalDate) {
        this.menuId = menuId;
        this.name = name;
        this.dietType = dietType;
        this.creationDate = creationDate;
        this.withdrawalDate = withdrawalDate;
    }

    public Menu(int menuId, String name, String dietType, LocalDate creationDate) {
        this.menuId = menuId;
        this.name = name;
        this.dietType = dietType;
        this.creationDate = creationDate;
        this.withdrawalDate = null;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getDietType() {
        return dietType;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    public String getName() {
        return name;
    }
}
