package com.xsn.gc;

public class Test1 {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        byte[] alloc1, alloc2, alloc3, alloc4;
        alloc1 = new byte[2 * _1MB];
        alloc2 = new byte[2 * _1MB];
        alloc3 = new byte[2 * _1MB];
        // alloc4 = new byte[4 * _1MB];
    }
}
