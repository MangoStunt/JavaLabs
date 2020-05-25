package edu.Cards;

import edu.options.DurationType;
import edu.options.OwnerType;

import java.util.Date;
import java.util.UUID;

public class TemporaryCard extends Card {
    private DurationType durationType;
    private Date expirationDate;
    public TemporaryCard(UUID id, OwnerType ownerType, DurationType durationType) {
        super(id, ownerType);
        this.durationType = durationType;
        expirationDate = new Date(new Date().getTime() + (long)durationType.getDaysLeft()*24*60*60*1000);
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public DurationType getDurationType() {
        return durationType;
    }

    @Override
    public boolean verify() {
        boolean isVerified = new Date().before(getExpirationDate());
        if(!isVerified) block();
        return isVerified;
    }

    @Override
    public String toString() {
        return "Card type: temporary card\n" +
               "Expiration date: " + getExpirationDate().toString() + "\n";
    }
}
