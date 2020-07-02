package com.xsn.thread;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor t1 = new ThreadPoolExecutor(
            2, 4, 1,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );

        Runnable r = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("test");
                TimeUnit.SECONDS.sleep(2);
            }
        };

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                t1.execute(r);
            } catch (RejectedExecutionException e) {
                System.out.println("==================");
                System.out.println(t1.getQueue().size());
                System.out.println(t1.getActiveCount());
                System.out.println("==================");
            }
        }

    }
}
