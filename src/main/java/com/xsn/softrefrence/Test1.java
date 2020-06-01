package com.xsn.softrefrence;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) {
        // ReferenceQueue queue = new ReferenceQueue();
        SoftReference<List> softList = new SoftReference<List>(new ArrayList<String>());

        try {
            List<String> temp = new ArrayList<String>();
            for (int i = 0; i < 100; i++) {
                temp.add("1");
            }
            System.out.println(softList.get() == null);

            while (true) {
                temp.addAll(temp);
                softList.get().size();
            }

        } catch (OutOfMemoryError oome) {
            System.out.println("OOM occured");
            System.out.println(softList == null);
            System.out.println(softList.get() == null);

        } catch (NullPointerException npe) {
            System.out.println("NPE occured");
            System.out.println(softList == null);
            System.out.println(softList.get() == null);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(softList == null);
            System.out.println(softList.get() == null);
        }
    }
}
