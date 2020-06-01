package com.xsn.softrefrence;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            temp.add("1");
        }

        List<String> list = new ArrayList<String>();
        SoftReference<List> softList = new SoftReference<List>(list);
        list = null;

        try {
            while (true) {
                softList.get().addAll(temp);
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
