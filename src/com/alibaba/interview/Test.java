package com.alibaba.interview;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws Exception {
        //		testAntMinStack();
//        testStatisticsCharactor();
//        testCalculator();
        testStatistics();
    }

    /**
     * Test 0ne.
     */
    public static void testAntMinStack() {

        System.out.println("AntMinStack test start......");
        System.out.println();

        AntMinStack antMinStack = new AntMinStack();
        antMinStack.push(100);
        antMinStack.push(200);
        antMinStack.push(99);
        antMinStack.push(250);
        antMinStack.push(800);

        System.out.println("stack after push:" + antMinStack);
        System.out.println("stack min value:" + antMinStack.min());
        System.out.println();

        System.out.println("stack pop " + antMinStack.pop());
        System.out.println("stack after pop:" + antMinStack);
        System.out.println("stack min value:" + antMinStack.min());
        System.out.println();

        System.out.println("stack pop " + antMinStack.pop());
        System.out.println("stack after pop:" + antMinStack);
        System.out.println("stack min value:" + antMinStack.min());
        System.out.println();

        System.out.println("stack pop " + antMinStack.pop());
        System.out.println("stack after pop:" + antMinStack);
        System.out.println("stack min value:" + antMinStack.min());
        System.out.println();

        System.out.println("AntMinStack test end......");

    }

    /**
     * Test two.
     */
    public static void testStatisticsCharactor() {
        long startTime = System.nanoTime();
        System.out.println("start time " + startTime);
        String charValue = "public"; //7970567089
        System.out.println(charValue + " count: " + StatisticsCharactor.count2(System.getProperty("user.dir") + "\\files\\bigFile.txt", charValue));
        System.out.println("spend time " + (System.nanoTime() - startTime));
    }

    /**
     * Test 3.
     */
    public static void testCalculator() {
        Calculator calc = new Calculator();
        String str = "2*(0-1+2)*(3+4)=";
        System.out.println(calc.doCalculate(str));
    }

    /**
     * Test 4.
     */
    public static void testStatistics() throws InterruptedException {


        Statistics statistics = new Statistics(System.getProperty("user.dir") + "\\files\\test4", 10);
        new Thread(() -> statistics.findMinQuota()).start();
        statistics.read();
        while (true) {
            if (statistics.isFinish()) {
                statistics.getMinMap().forEach((key, value) -> value.values().forEach(item -> {
                    System.out.println(item);
                }));
                break;
            }
        }

    }
}
