package com.berg;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main extends Thread {

  static Random r = new Random();
  static Integer size = null;
  static Integer population = null;
  static Scanner scanner = new Scanner(System.in);
  static Long starttime;


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

    //StraightWay
    starttime = System.nanoTime();
    System.out.println(straightWay(size, population));
    System.out.println("Time in nanos: " + (System.nanoTime() - starttime));

    //Recursive Split Way
    starttime = System.nanoTime();
    ConcurrentSkipListSet<Integer> results = new ConcurrentSkipListSet<Integer>() {
      @Override
      public boolean add(Integer integer) {
        synchronized (size) {
          if (this.size() < size) {
            return super.add(integer);
          }
          return false;
        }
      }
    };
    Thread t = new Thread(new RecursiveSplitWay(size, 1, 1 + population, results, r, 1));
    t.start();
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println("Recursive Split Way: Time in nanos: " + (System.nanoTime() - starttime));

    //Set # of Threads
    Integer THREADCOUNT = 2 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 4 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 6 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 8 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 10 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 12 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 14 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 16 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = 32 ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));

    THREADCOUNT = size ;
    starttime = System.nanoTime();
    results = new ConcurrentSkipListSet<Integer>();
    setThreadCountStraightWay(size, population, results, r, THREADCOUNT);
    while (results.size() < size) {
    }
    System.out.println(results);
    System.out.println(THREADCOUNT+" Threads: Time in nanos: " + (System.nanoTime() - starttime));
  }

  private static void setThreadCountStraightWay(Integer size, Integer population,
      ConcurrentSkipListSet<Integer> results, Random r, int threads) {
    for (int i = 0; i < threads; i++) {
      Thread t = new Thread() {
        @Override
        public void run() {
          while(results.size() < size) {
            results.add(1+r.nextInt(population));
          }
        }
      };
      t.start();
    }
  }

  private static TreeSet<Integer> straightWay(Integer size, Integer population) {
    TreeSet<Integer> results = new TreeSet<Integer>();
    while (results.size() < size) {
      results.add(1 + r.nextInt(population));
    }
    return results;
  }
}

class RecursiveSplitWay implements Runnable {

  Integer size;
  Integer zero;
  Integer population;
  ConcurrentSkipListSet<Integer> results;
  Random r;
  Integer id;

  public RecursiveSplitWay(Integer size, Integer zero, Integer population,
      ConcurrentSkipListSet<Integer> results, Random r, Integer id) {
    this.size = size;
    this.zero = zero;
    this.population = population;
    this.results = results;
    this.r = r;
    this.id = id;
  }

  @Override
  public void run() {
    Integer random = zero + r.nextInt(population - zero);
    if (results.add(random)) {
      if (size - results.size() > 0) {
        recursiveSplitWay(size, zero, random, population, results, r, id);
      }
    }
  }

  private void recursiveSplitWay(Integer size, Integer zero, Integer random, Integer population,
      ConcurrentSkipListSet<Integer> results, Random r, Integer id) {
    if (random - zero > 0) {
      Thread t1 = new Thread(new RecursiveSplitWay(size, zero, random, results, r, 1 + id));
      t1.start();
    }
    if (population - (random + 1) > 0) {
      Thread t2 = new Thread(
          new RecursiveSplitWay(size, random + 1, population, results, r, 2 + id));
      t2.start();
    }
  }
}




