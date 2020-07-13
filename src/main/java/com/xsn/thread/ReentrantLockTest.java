package com.xsn.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    // true公平锁  false非公平锁
    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    action();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "fair-lock-" + i).start();
        }
    }

    private static void action() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "试图获取锁");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "成功获取锁");

        TimeUnit.SECONDS.sleep(2);

        System.out.println("-------" +Thread.currentThread().getName()+ "-start1----");
        System.out.println(lock.getHoldCount());
        System.out.println(lock.getQueueLength());
        System.out.println(lock.isFair());
        System.out.println(lock.isLocked());
        System.out.println("-------" +Thread.currentThread().getName()+ "-end1----");

        lock.lock();
        System.out.println(Thread.currentThread().getName() + "第二次加锁");
        System.out.println("-------" +Thread.currentThread().getName()+ "-start2----");
        System.out.println(lock.getHoldCount());
        System.out.println(lock.getQueueLength());
        System.out.println(lock.isFair());
        System.out.println(lock.isLocked());
        System.out.println("-------" +Thread.currentThread().getName()+ "-end2----");


        TimeUnit.SECONDS.sleep(2);

        System.out.println(Thread.currentThread().getName() + "第一次解锁");
        lock.unlock();
        TimeUnit.SECONDS.sleep(2);

        System.out.println(Thread.currentThread().getName() + "第二次解锁");
        lock.unlock();
    }
}
