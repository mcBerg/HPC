package com.berg;

import java.util.Random;
import java.util.TreeSet;


public class StraightWay {

    TreeSet<Integer> results = new TreeSet<>();
    Random r = new Random();
    Integer size;
    Integer population;

    StraightWay(Integer size, Integer population) {
        this.size = size;
        this.population = population;
    }

    public void start() {
        while (results.size() < size) {
            results.add(1 + r.nextInt(population));
        }
        System.out.println(results.toString());
    }
}