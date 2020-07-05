package com.xsn.container;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList();


        list.add(3, "2");

        list.addAll(new ArrayList<String>() {
            {
                add("3");
                add("4");
            }
        });

        list.addAll(2, new ArrayList<String>() {
            {
                add("3");
                add("4");
            }
        });

        list.addFirst("0");
        list.addLast("end");

        list.clear();
        list.contains("1");

        list.element();
    }
}
