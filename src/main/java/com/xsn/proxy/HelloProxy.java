package com.xsn.proxy;

public class HelloProxy implements Hello {
    private Hello hello = new HelloImpl();

    @Override
    public void hello() {
        System.out.println("before proxy");
        hello.hello();
        System.out.println("after proxy");
    }
}
