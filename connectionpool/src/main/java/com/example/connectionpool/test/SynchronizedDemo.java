package com.example.connectionpool.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author m
 * @date 2020/1/3 16:26
 */
public class SynchronizedDemo implements Runnable {
    private static volatile int count ;
    private static boolean flag=true;
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
    }

    @Override
    public void run() {
        //count++
        //=temp=count
        //count=count+1
        for (int i = 0,j=0; i < 10;) {

        }
    }
}
