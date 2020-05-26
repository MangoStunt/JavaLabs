package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private static List<Account> accounts = new ArrayList<>();

    public static long getTotalSum() {
        long sum = 0;
        for (Account account : accounts) {
            sum += account.getTotal();
        }
        return sum;
    }

    public static void addAccount(int amount) {
        accounts.add(new Account(amount));
    }

    public static void transfer(Account from, Account to, int amount) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
            from.withdraw(amount);
            to.deposit(amount);
        } catch (NotEnoughMoneyException e) {
            System.err.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public static Account getRandomAccount() {
        Random random = new Random();
        return accounts.get(random.nextInt(accounts.size() - 1));
    }
}
