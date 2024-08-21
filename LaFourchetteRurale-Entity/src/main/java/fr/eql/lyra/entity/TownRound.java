package fr.eql.lyra.entity;
import java.io.Serializable;

public class TownRound implements Serializable {
    private int townId;
    private int roundId;

    public TownRound(int townId, int roundId) {
        this.townId = townId;
        this.roundId = roundId;
    }

    public int getTownId() {
        return townId;
    }

    public int getRoundId() {
        return roundId;
    }
}
