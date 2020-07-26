package com.xsn.tx;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        log.info("hello");
    }
}
