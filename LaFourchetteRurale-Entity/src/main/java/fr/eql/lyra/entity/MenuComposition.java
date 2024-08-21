package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class MenuComposition implements Serializable {
    private final int compositionId;
    private final int dishId;
    private final int menuId;
    private final LocalDate compositionDate;

    //CONSTRUCTOR
    public MenuComposition(int compositionId, int dishId, int menuId, LocalDate compositionDate) {
        this.compositionId = compositionId;
        this.dishId = dishId;
        this.menuId = menuId;
        this.compositionDate = compositionDate;
    }

    //GETTER
    public int getCompositionId() {
        return compositionId;
    }

    public int getDishId() {
        return dishId;
    }

    public int getMenuId() {
        return menuId;
    }

    public LocalDate getCompositionDate() {
        return compositionDate;
    }
}
