package edu.Cards;

import edu.options.OwnerType;

import java.util.UUID;

public abstract class Card {
    private UUID id;
    private OwnerType ownerType;
    private boolean isBlocked;
    public Card(UUID id, OwnerType ownerType) {
        this.id = id;
        this.ownerType = ownerType;
        isBlocked = false;
    }

    public UUID getID(){
        return id;
    }
    public OwnerType getOwnerType() {
        return ownerType;
    }

    public abstract boolean verify();

    public abstract String toString();

    public boolean isBlocked(){
        return isBlocked;
    }

    public void block(){
        isBlocked = true;
    }

}
