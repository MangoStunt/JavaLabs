package edu;

import java.util.Date;
import java.util.UUID;

public class Pass {
    private Date passDate;
    private boolean passed;
    private int turnstileId;
    private UUID cardId;
    private ControlSystem controlSystem;

    public Pass(boolean passed, int turnstileId, UUID cardId, ControlSystem controlSystem) {
        this.passed = passed;
        this.turnstileId = turnstileId;
        this.cardId = cardId;
        passDate = new Date(new Date().getTime());
        this.controlSystem = controlSystem;
    }

    public Date getPassDate() {
        return passDate;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getTurnstileId() {
        return turnstileId;
    }

    public UUID getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return controlSystem.getCardInfo(cardId) +
               "Pass result: " + passed + "\n" +
               "Turnstile id: " + turnstileId  + "\n";
    }
}
