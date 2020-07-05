package com.xsn.gc;

public class Test3 {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] alloc1 = new byte[_1MB / 4];
        byte[] alloc2 = new byte[_1MB * 4];
        byte[] alloc3 = new byte[_1MB * 4];

        alloc3 = null;

        alloc3 = new byte[_1MB * 4];
    }
}
