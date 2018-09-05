package com.berg;

import java.util.Random;
import java.util.TreeSet;

public class SplitWay {

    static TreeSet<Integer> results = new TreeSet<>();
    static Random r = new Random();
    static SplitChild[] children;

    private Integer size;
    private Integer population;

    public SplitWay(Integer size, Integer population) {
        this.size = size;
        this.population = population;
        children = new SplitChild[size];
    }

    public void start() {
        if (results.size() < size) {
            children[0] = new SplitChild(0, population);
            results.add(children[0].pick);
            int x = 0;
            while (results.size() < size) {
                children[x].populate();
                if (children[x].one != null) {
                    children[results.size()] = children[x].one;
                    results.add(children[x].one.pick);
                }
                if (results.size() < size) {
                    if (children[x].two != null) {
                        children[results.size()] = children[x].two;
                        results.add(children[x].two.pick);
                    }
                }
                x++;
            }
        }
        print();
    }

    public void print() {
        System.out.println(results.toString());
    }
}
