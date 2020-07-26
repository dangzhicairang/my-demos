package com.xsn.tx;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        /*HelloService target = new HelloServiceImpl();
        HelloService proxy = new HelloProxy(target);
        proxy.sayHello();*/

        HelloService proxy = (HelloService) Proxy.newProxyInstance(
                HelloService.class.getClassLoader()
                , new Class[]{HelloService.class}
                , new DynamicProxy(new HelloServiceImpl())
        );
        proxy.sayHello();
    }
}
