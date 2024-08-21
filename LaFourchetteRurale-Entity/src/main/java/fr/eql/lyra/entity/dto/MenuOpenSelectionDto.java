package fr.eql.lyra.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class MenuOpenSelectionDto implements Serializable {
    private int idMenuOpenSelection;
    private int idTypeRepas;
    private int idMenu;
    private LocalDate selectionOpening;
    private LocalDate selectingClosing;
    private boolean isDefault;
    private int dayPosition;


    public int getIdMenuOpenSelection() {
        return idMenuOpenSelection;
    }

    public void setIdMenuOpenSelection(int idMenuOpenSelection) {
        this.idMenuOpenSelection = idMenuOpenSelection;
    }

    public int getIdTypeRepas() {
        return idTypeRepas;
    }

    public void setIdTypeRepas(int idTypeRepas) {
        this.idTypeRepas = idTypeRepas;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public LocalDate getSelectionOpening() {
        return selectionOpening;
    }

    public void setSelectionOpening(LocalDate selectionOpening) {
        this.selectionOpening = selectionOpening;
    }

    public LocalDate getSelectingClosing() {
        return selectingClosing;
    }

    public void setSelectingClosing(LocalDate selectingClosing) {
        this.selectingClosing = selectingClosing;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public int getDayPosition() {
        return dayPosition;
    }

    public void setDayPosition(int dayPosition) {
        this.dayPosition = dayPosition;
    }
}

