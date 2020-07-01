package com.xsn.thread;

import lombok.extern.log4j.Log4j;

import java.util.concurrent.TimeUnit;

@Log4j
public class NotificationTest {

    private volatile static boolean FLAG = false;

    public static void main(String[] args) throws InterruptedException {
        NotificationTest notificationTest = new NotificationTest();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                notificationTest.action();
                System.out.println(Thread.currentThread().getName()
                    + "执行完毕");
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                notificationTest.notification();
                System.out.println(Thread.currentThread().getName()
                    + "执行完毕");
            }
        };

        Thread w1 = new Thread(r1, "w1");
        Thread w2 = new Thread(r1, "w2");
        Thread w3 = new Thread(r1, "w3");
        Thread n = new Thread(r2, "n");

        w1.start();
        w2.start();
        w3.start();

        TimeUnit.SECONDS.sleep(10);

        n.start();
    }

    synchronized void action() {
        try {
            while (!FLAG) {
                System.out.println(Thread.currentThread().getName()
                        + "开始执行");
                wait();
                System.out.println(Thread.currentThread().getName()
                        + "线程进入wait队列");
            }
            FLAG = false;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    synchronized void notification() {
        FLAG = true;
        // notifyAll();

        notify();
    }
}
