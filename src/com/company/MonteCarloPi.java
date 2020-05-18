package com.company;

public class MonteCarloPi {
    public final long iterationsTotal = (long) 1e9;
    public double[] getPiParallel() throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        long passedTotal = 0, iterationsPerProcess = iterationsTotal / n;
        CustomThread[] customThreads = new CustomThread[n];
        for(int i = 0; i < n; i++) {
            customThreads[i] = new CustomThread(iterationsPerProcess);
            customThreads[i].start();
        }
        for (CustomThread customThread : customThreads) {
            customThread.join();
            passedTotal += customThread.passed;
        }
        double[] data = {((double) passedTotal / iterationsTotal) * 4.0, customThreads.length};
        return data;
    }
}
