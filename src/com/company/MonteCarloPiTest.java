package com.company;

import org.junit.Test;
import static org.junit.Assert.*;

public class MonteCarloPiTest {

    @Test
    public void getPiParallel() throws InterruptedException{
        long start = 0;
        System.out.println("Optimal count (?):" + Runtime.getRuntime().availableProcessors());
        for(int i = 1; i <= 8; i++) {
            start = System.currentTimeMillis();
            System.out.println("Pi: " + new MonteCarloPi().getPiParallel()[0]);
            System.out.println(System.currentTimeMillis() - start + "ms (" + i +"threads)");
            System.out.println("------------------------------");
        }
    }
}