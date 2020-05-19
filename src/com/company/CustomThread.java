package com.company;

import java.util.Random;

public class CustomThread extends Thread {
    public long passed;
    private long iterationsPerProcess;

    public CustomThread(long iterationsPerProcess) {
        this.iterationsPerProcess = iterationsPerProcess;
    }

    public void run() {
        Random rnd = new Random();
        double x, y;
        passed = 0;
        for(int i = 0; i < iterationsPerProcess; i++) {
            x = rnd.nextDouble();
            y = rnd.nextDouble();
            if((x * x + y * y) < 1.0)
                passed++;
        }
    }
}
