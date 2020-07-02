package com.xsn.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> in = new ArrayList<>();
        List<String> list = new ArrayList<>(in);
        list.add("1");
        list.add(1, "2");

        list.get(1);

        list.contains(1);

        list.remove(1);

        list.remove("1");

        list.addAll(new ArrayList<String>() {
            {
                add("2");
                add("3");
            }
        });

        list.removeAll(new ArrayList<String>() {
            {
                add("1");
                add("2");
            }
        });

        Iterator it = list.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }
}
