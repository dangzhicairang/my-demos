package com.xsn.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ParkTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("---------m1----------");

        Thread t1 = new Thread(() -> {
            System.out.println("-------t11-------");
            LockSupport.park();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("-------t12-------");
        }, "t1");

        t1.start();

        TimeUnit.MILLISECONDS.sleep(10);

        Thread t2 = new Thread(() -> {
            System.out.println("-------t21-------");
            LockSupport.unpark(t1);
            System.out.println("-------t22-------");
        }, "t2");

        t2.start();
    }


}
