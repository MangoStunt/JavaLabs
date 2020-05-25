package edu;

import edu.Cards.Card;
import edu.Cards.CumulativeCard;
import edu.Cards.MultiJourneyCard;
import edu.Cards.TemporaryCard;
import edu.options.DurationType;
import edu.options.OwnerType;
import edu.options.TravelsCountType;
import java.util.ArrayList;
import java.util.UUID;

public class ControlSystem {
    private  static ControlSystem instance;
    private ArrayList<Card> cards = new ArrayList<>();
    public ArrayList<Turnstile> turnstiles = new ArrayList<>();
    public ArrayList<Pass> passes = new ArrayList<>();
    public int travelPrice = 4;
    private static int turnstilesCount = 0;
    private enum CardType {
        CUMULATIVE, MULTIJOURNEY, TEMPORARY, NULL
    }
    private ControlSystem() {
    }

    public static ControlSystem getInstance() {
        if(instance == null) {
            instance = new ControlSystem();
        }
        return instance;
    }

    public static void deleteInstance() {
        if(instance != null) instance = null;
    }

    public void createTurnstile() {
        turnstiles.add(new Turnstile(this, turnstilesCount++));
    }

    public void createCard(int balance) {
        cards.add(new CumulativeCard(UUID.randomUUID(), balance, travelPrice));
    }

    public void createCard(OwnerType ownerType, TravelsCountType travelsCountType) {
        cards.add(new MultiJourneyCard(UUID.randomUUID(), ownerType, travelsCountType));
    }

    public void createCard(OwnerType ownerType, DurationType durationType) {
        cards.add(new TemporaryCard(UUID.randomUUID(), ownerType, durationType));
    }

    public Card getCardById(UUID id) {
        for (Card card : cards) {
            if(card.getID() == id)
                return card;
        }
        return null;
    }

    public String getCardInfo(UUID id) {
        Card card = getCardById(id);
        return "Card ID: " + card.getID() + "\n" +
               "Owner type: " + card.getOwnerType().toString() + "\n" +
               card.toString();
    }

    public void removeBlockedCards(){
        for (Card card : cards)
            if(card.isBlocked()) cards.remove(card);

    }

    public Card getCardByIndex(int index){
        return cards.get(index);
    }

    public ArrayList<CumulativeCard> getCumulativeCardList() {
        ArrayList<CumulativeCard> cumulativeCardsList = new ArrayList<>();
        for(Card card : cards) {
           if(card instanceof CumulativeCard) cumulativeCardsList.add((CumulativeCard)card);
        }
        return null;
    }

    public ArrayList<TemporaryCard> getTemporaryCardList() {
        ArrayList<TemporaryCard> temporaryCardsList = new ArrayList<>();
        for(Card card : cards) {
            if(card instanceof TemporaryCard) temporaryCardsList.add((TemporaryCard)card);
        }
        return null;
    }

    public ArrayList<MultiJourneyCard> getMultiJourneyCardList() {
        ArrayList<MultiJourneyCard> multiJourneyCardsList = new ArrayList<>();
        for(Card card : cards) {
            if(card instanceof MultiJourneyCard) multiJourneyCardsList.add((MultiJourneyCard)card);
        }
        return null;
    }

    public int getCountOfTries(){
        return passes.size();
    }

    public int getCountOfPasses() {
        int res = 0;
        for (Pass pass : passes) {
            if(pass.isPassed()) res++;
        }
        return res;
    }

    public int getCountOfFails(){
        return passes.size() - getCountOfPasses();
    }

    public int getCountOfPassesByCumulativeCard() {
        int res = 0;
        for (Pass pass : passes) {
            if(getCardById(pass.getCardId()) instanceof CumulativeCard && pass.isPassed()) res++;
        }
        return res;
    }

    public int getCountOfTriesByCumulativeCard() {
        int res = 0;
        for (Pass pass : passes) {
            if(getCardById(pass.getCardId()) instanceof CumulativeCard) res++;
        }
        return res;
    }

    public int getCountOfFailsByCumulativeCard() {
        return getCountOfTriesByCumulativeCard() - getCountOfPassesByCumulativeCard();
    }

    public int getCountOfTriesByMultiJourneyCard(String ownType, String trCountType) {
        int res = 0;
        OwnerType ownerType;
        TravelsCountType travelsCountType;
        try{
            ownerType = OwnerType.valueOf(ownType.toUpperCase());
            travelsCountType = TravelsCountType.valueOf(trCountType.toUpperCase());
        }
        catch (Exception e){
            return -1;
        }
        for (Pass pass : passes) {
            Card card = getCardById(pass.getCardId());
            if(card instanceof MultiJourneyCard) {
                MultiJourneyCard multiJourneyCard = (MultiJourneyCard)card;
                if(multiJourneyCard.getOwnerType() == ownerType
                        && multiJourneyCard.getTravelsCountType() == travelsCountType) res++;
            }
        }
        return res;
    }

    public int getCountOfPassesByMultiJourneyCard(String ownType, String trCountType) {
        int res = 0;
        OwnerType ownerType;
        TravelsCountType travelsCountType;
        try {
            ownerType = OwnerType.valueOf(ownType.toUpperCase());
            travelsCountType = TravelsCountType.valueOf(trCountType.toUpperCase());
        }
        catch (Exception e){
            return -1;
        }
        for (Pass pass : passes) {
            Card card = getCardById(pass.getCardId());
            if(card instanceof MultiJourneyCard) {
                MultiJourneyCard multiJourneyCard = (MultiJourneyCard)card;
                if(multiJourneyCard.getOwnerType() == ownerType
                        && multiJourneyCard.getTravelsCountType() == travelsCountType
                        && pass.isPassed()) res++;
            }
        }
        return res;
    }

    public int getCountOfFailsByMultiJourneyCard(String ownType, String trCountType) {
        return getCountOfTriesByMultiJourneyCard(ownType, trCountType) - getCountOfPassesByMultiJourneyCard(ownType, trCountType);
    }

    public int getCountOfTriesByTemporaryCard(String ownType, String durType) {
        int res = 0;
        OwnerType ownerType;
        DurationType durationType;
        try {
            ownerType = OwnerType.valueOf(ownType.toUpperCase());
            durationType = DurationType.valueOf(durType.toUpperCase());
        }
        catch (Exception e){
            return -1;
        }
        for (Pass pass : passes) {
            Card card = getCardById(pass.getCardId());
            if(card instanceof TemporaryCard) {
                TemporaryCard tempCard = (TemporaryCard) card;
                if (card.getOwnerType() == ownerType
                        && tempCard.getDurationType() == durationType) res++;
            }
        }
        return res;
    }

    public int getCountOfPassesByTemporaryCard(String ownType, String durType) {
        int res = 0;
        OwnerType ownerType;
        DurationType durationType;
        try {
            ownerType = OwnerType.valueOf(ownType.toUpperCase());
            durationType = DurationType.valueOf(durType.toUpperCase());
        }
        catch (Exception e){
            return -1;
        }
        for (Pass pass : passes) {
            Card card = getCardById(pass.getCardId());
            if(card instanceof TemporaryCard) {
                TemporaryCard tempCard = (TemporaryCard) card;
                if (tempCard.getOwnerType() == ownerType
                        && tempCard.getDurationType() == durationType
                        && pass.isPassed()) res++;
            }
        }
        return res;
    }

    public int getCountOfFailsByTemporaryCard(String ownType, String durType) {
        return getCountOfTriesByTemporaryCard(ownType, durType) - getCountOfPassesByTemporaryCard(ownType, durType);
    }

}
