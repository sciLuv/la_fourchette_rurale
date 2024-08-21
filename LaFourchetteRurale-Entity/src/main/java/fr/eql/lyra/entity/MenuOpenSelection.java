package fr.eql.lyra.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class MenuOpenSelection implements Serializable {
    private final int openMenuSelectionId;
    private final int mealTypeId;
    private final int menuId;
    private final LocalDate selectionOpening;
    private final LocalDate selectionClosing;
    private final boolean defaultMenu;
    private final int dayPosition;

    public MenuOpenSelection(int openMenuSelectionId, int mealTypeId, int menuId,
                             LocalDate selectionOpening, LocalDate selectionClosing,
                             boolean defaultMenu, int dayPosition) {
        this.openMenuSelectionId = openMenuSelectionId;
        this.mealTypeId = mealTypeId;
        this.menuId = menuId;
        this.selectionOpening = selectionOpening;
        this.selectionClosing = selectionClosing;
        this.defaultMenu = defaultMenu;
        this.dayPosition = dayPosition;
    }

    public int getOpenMenuSelectionId() {
        return openMenuSelectionId;
    }

    public int getMealTypeId() {
        return mealTypeId;
    }

    public int getMenuId() {
        return menuId;
    }

    public LocalDate getSelectionOpening() {
        return selectionOpening;
    }

    public LocalDate getSelectionClosing() {
        return selectionClosing;
    }

    public boolean getDefaultMenu() {
        return defaultMenu;
    }

    public int getDayPosition() {
        return dayPosition;
    }
}
