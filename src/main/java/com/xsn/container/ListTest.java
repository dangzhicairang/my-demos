package com.xsn.container;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> in = new ArrayList<>();
        List<Integer> list = new ArrayList<>(in);
        list.add(1);
        list.add(2, 2);

        list.get(1);

        list.contains(1);

        list.remove(1);
    }
}
