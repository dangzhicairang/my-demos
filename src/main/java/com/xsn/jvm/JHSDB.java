package com.xsn.jvm;

/**
 * -Xmx10M -XX:+UseSerialGC -XX:-UseCompressdOops
 */
public class JHSDB {
    static class Test {
        static ObjectHolder objectHolder = new ObjectHolder();

        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static  class ObjectHolder {}

    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }
}
