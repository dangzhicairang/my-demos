package com.xsn.thread;

public class Basic {
    public static void main(String[] args) {
        Basic basic = new Basic();
        Thread t1 = new MyThread();
        t1.setName("t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " hello2");
        });
        t2.setName("t2");
        t2.start();
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " hello1");
        }
    }
}
