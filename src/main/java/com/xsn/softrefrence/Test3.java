package com.xsn.softrefrence;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        SoftReference<List> softList = new SoftReference<List>(new ArrayList<String>());

        try {
            List<String> temp = new ArrayList<String>();
            for (int i = 0; i < 100; i++) {
                temp.add("1");
            }

            while (true) {
                temp.addAll(temp);
                softList.get().size();
            }

        } catch (OutOfMemoryError oome) {
            System.out.println("OOM occured");
        }

        softList.get().add("1");
    }
}
