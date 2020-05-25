package edu;

import edu.Cards.CumulativeCard;
import edu.Cards.MultiJourneyCard;
import edu.Cards.TemporaryCard;

public class Turnstile {
    private ControlSystem controlSystem;
    private int id;
    public Turnstile(ControlSystem controlSystem, int id) {
        this.controlSystem = controlSystem;
        this.id = id;
    }

    public boolean acceptPass(CumulativeCard cumulativeCard) {
        boolean passes = cumulativeCard.verify();
        controlSystem.passes.add(new Pass(passes, id, cumulativeCard.getID(), controlSystem));
        return passes;
    }
    public boolean acceptPass(MultiJourneyCard multiJourneyCard) {
        boolean passes = multiJourneyCard.verify();
        controlSystem.passes.add(new Pass(passes, id, multiJourneyCard.getID(), controlSystem));
        return passes;
    }
    public boolean acceptPass(TemporaryCard temporaryCard) {
        boolean passes = temporaryCard.verify();
        controlSystem.passes.add(new Pass(passes, id, temporaryCard.getID(), controlSystem));
        return passes;
    }

}
