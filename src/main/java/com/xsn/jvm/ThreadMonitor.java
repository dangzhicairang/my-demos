package com.xsn.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadMonitor {
    public static void createBusyThread() {
        Thread t = new Thread(() -> {
           while (true);
        }, "testBusyThread");

        t.start();
    }

    public static void createLockThread(final Object lock) {
        Thread t = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");

        t.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        createBusyThread();
        br.readLine();

        Object obj = new Object();
        createLockThread(obj);
    }
}
