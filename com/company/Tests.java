package com.company;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

class Tests implements Runnable {

    @Test
    void getTotalSum() {
        Random random = new Random();
        int threads = 10000;
        for (int i = 0; i < 100; i++) {
            Bank.addAccount(Math.abs(random.nextInt() * 1000));
        }

        long before = Bank.getTotalSum();
        System.out.println(before);

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            executor.submit(this);
        }

        executor.shutdown();

        long after = Bank.getTotalSum();

        System.out.println(after);
        System.out.println("------------------------");
        System.out.println(after - before);

        assertEquals(before, after);
    }

    @Override
    public void run() {
        Random random = new Random();
        Account from = Bank.getRandomAccount();
        Account to = Bank.getRandomAccount();
        System.out.println("----------------------------------------");
        System.out.println("from id: " + from.id + " to id: " + to.id);

        Bank.transfer(from, to, random.nextInt(100));
    }
}