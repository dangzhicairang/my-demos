package com.xsn.tx;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class DynamicProxy implements InvocationHandler {

    private HelloService target;

    public DynamicProxy(HelloService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("dynamic proxy");
        return method.invoke(target, args);
    }
}
