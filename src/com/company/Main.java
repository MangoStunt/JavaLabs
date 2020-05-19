package com.company;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        long startTime, finishTime, operations;
        double parPi;
        int threads;
        startTime = System.currentTimeMillis();
        parPi = new MonteCarloPi().getPiParallel()[0];
        threads = (int) new MonteCarloPi().getPiParallel()[1];
        finishTime = System.currentTimeMillis() - startTime;
        operations = new MonteCarloPi().iterationsTotal;
        System.out.println("Pi: " + parPi);
        System.out.println("Threads: " + threads);
        System.out.println("Operations total: "+ operations);
        System.out.println(finishTime + " ms");
    }
}
