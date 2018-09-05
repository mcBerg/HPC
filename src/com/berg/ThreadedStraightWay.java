package com.berg;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

public class ThreadedStraightWay implements Runnable {

    final ConcurrentSkipListSet<Integer> results = new ConcurrentSkipListSet<>();
    Random r = new Random();
    Integer size;
    Integer population;
    Integer threads;


    public ThreadedStraightWay(Integer size, Integer population, Integer threads) {
        this.size = size;
        this.population = population;
        this.threads = threads;
    }

    public void callStart() {
        Thread[] threadArray = new Thread[threads];
        for (int x = 0; x < threads; x++) {
            threadArray[x] = new Thread(this);
            threadArray[x].start();
        }
        for (Thread t : threadArray) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(results.size() + " " + results);
    }

    @Override
    public void run() {
        while (results.size() < size) {
            synchronized (results) {
                results.add(1 + r.nextInt(population));
            }
        }
    }
}
