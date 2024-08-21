package fr.eql.lyra.entity;

import java.io.Serializable;

public class FoodSpecificityFavorite implements Serializable {
    private long adherentId;
    private long dietarySpecificityId;

    public FoodSpecificityFavorite(long adherentId, long dietarySpecificityId) {
        this.adherentId = adherentId;
        this.dietarySpecificityId = dietarySpecificityId;
    }

    public long getAdherentId() {
        return adherentId;
    }

    public void setAdherentId(long adherentId) {
        this.adherentId = adherentId;
    }

    public long getDietarySpecificityId() {
        return dietarySpecificityId;
    }

    public void setDietarySpecificityId(long dietarySpecificityId) {
        this.dietarySpecificityId = dietarySpecificityId;
    }
}
