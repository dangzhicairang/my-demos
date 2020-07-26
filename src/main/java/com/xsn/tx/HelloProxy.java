package com.xsn.tx;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloProxy implements HelloService {

    private HelloService target;

    public HelloProxy(HelloService target) {
        this.target = target;
    }

    @Override
    public void sayHello() {
        log.info("before action");

        target.sayHello();

        log.info("after action");
    }
}
