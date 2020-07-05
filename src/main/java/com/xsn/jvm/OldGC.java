package com.xsn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100M -Xmx100M -XX:+UseSerialGC
 */
public class OldGC {
    static  class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }

        // list = null;

    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        System.gc();
        System.out.println();
    }
}
