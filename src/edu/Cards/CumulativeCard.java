package edu.Cards;

import edu.options.OwnerType;

import java.util.UUID;

public class CumulativeCard extends Card {
    private int balance;
    private int travelPrice;

    public CumulativeCard(UUID id, int balance, int travelPrice) {
        super(id, OwnerType.DEFAULT);
        this.balance = balance;
        this.travelPrice = travelPrice;
    }
    public void replenish(int amount){
        balance += amount;
    }
    public boolean withdraw() {
        if(balance >= travelPrice){
            balance -= travelPrice;
            return true;
        }
        return false;
    }
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean verify()
    {
        return withdraw();
    }

    @Override
    public String toString() {
        return "Card type: cumulative card\n" +
               "Balance: " + balance + "\n";
    }
}
