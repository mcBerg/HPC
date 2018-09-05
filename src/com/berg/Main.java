package com.berg;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    static Integer size = null;
    static Integer population = null;
    static Scanner scanner = new Scanner(System.in);
    static Long starttime;

    static Map<String, Long> resultComparison = new LinkedHashMap<>();

    public static void main(String[] args) {
        while (size == null) {
            System.out.println("Sample Size:");
            size = Integer.valueOf(scanner.nextLine());
        }

        while (population == null) {
            System.out.println("Sample Population:");
            population = Integer.valueOf(scanner.nextLine());
        }

        System.out.println("Will pick " + size + " from " + population);

        //Straight Way
        starttime = System.nanoTime();
        new StraightWay(size, population).start();
        resultComparison.put("Straight Way ", System.nanoTime() - starttime);

        //Split Way
        starttime = System.nanoTime();
        new SplitWay(size, population).start();
        resultComparison.put("Split Way ", System.nanoTime() - starttime);

        //2 Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, 2).callStart();
        resultComparison.put("2 Threads Straight Way", System.nanoTime() - starttime);

        //4 Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, 4).callStart();
        resultComparison.put("4 Threads Straight Way", System.nanoTime() - starttime);

        //7 Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, 7).callStart();
        resultComparison.put("7 Threads Straight Way", System.nanoTime() - starttime);

        //8 Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, 8).callStart();
        resultComparison.put("8 Threads Straight Way", System.nanoTime() - starttime);

        //9 Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, 9).callStart();
        resultComparison.put("9 Threads Straight Way", System.nanoTime() - starttime);

        //16 Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, 16).callStart();
        resultComparison.put("16 Threads Straight Way", System.nanoTime() - starttime);

        Integer cores = Runtime.getRuntime().availableProcessors();
        //cores Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, cores).callStart();
        resultComparison.put("Cores(" + cores + ") Threads Straight Way", System.nanoTime() - starttime);

        //Size Threaded Straight Way
        starttime = System.nanoTime();
        new ThreadedStraightWay(size, population, size).callStart();
        resultComparison.put(size + " Threads Straight Way", System.nanoTime() - starttime);

        for (String key : resultComparison.keySet()) {
            System.out.format("%-30s", key);
            System.out.format("%15d", resultComparison.get(key));
            System.out.println();
        }
    }
}
