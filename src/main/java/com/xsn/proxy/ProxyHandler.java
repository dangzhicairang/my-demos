package com.xsn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyHandler implements InvocationHandler {
    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before proxy " + method.getName());
        method.invoke(object, args);
        System.out.println("after proxy " + method.getName());
        return null;
    }

    public static void main(String[] args) {
        Hello hello = new HelloImpl();

        InvocationHandler invocationHandler = new ProxyHandler(hello);

        Hello proxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(), invocationHandler);

        proxy.hello();
    }
}
