package com.xsn.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CallableTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy()
        );

        Callable<String> c = new Callable() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return "callable";
            }
        };

        try {
            // Future<String> f = c.call();
        } catch (Exception e) {

        }

        try {
            TestDto testDto = new TestDto();
            Future<TestDto> f1 = executor.submit(new MyRunnable(testDto), testDto);
            System.out.println("===" + f1.get());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        Future<String> f1 = executor.submit(c);

        try {
            System.out.println("===" + f1.get(1, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        try {
            System.out.println("===" + f1.get());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    static class MyRunnable implements Runnable {
        TestDto testDto;

        public MyRunnable (TestDto testDto) {
            this.testDto = testDto;
        }

        @Override
        public void run() {
            System.out.println("runable");
            testDto.setA("a");
            testDto.setB("b");
        }
    }
}
