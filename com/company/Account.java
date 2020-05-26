package com.company;

public class Account {
    private static int idTemplate = 0;
    public int id;
    private int total;

    public Account(int total) {
        this.id = idTemplate++;
        this.total = total;
    }

    public void withdraw(int amount) throws NotEnoughMoneyException {
        if (total >= amount) {
            total -= amount;
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    public void deposit(int amount) {
        total += amount;
    }

    public int getTotal() {
        return total;
    }
}
