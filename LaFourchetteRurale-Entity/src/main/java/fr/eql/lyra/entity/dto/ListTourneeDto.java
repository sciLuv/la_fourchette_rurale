package fr.eql.lyra.entity.dto;

import java.io.Serializable;
import java.util.List;

public class ListTourneeDto implements Serializable {

    TourneeDto[] tourneeDtos;

    public TourneeDto[] getTourneeDtos() {
        return tourneeDtos;
    }

    public void setTourneeDtos(TourneeDto[] tourneeDtos) {
        this.tourneeDtos = tourneeDtos;
    }
}
