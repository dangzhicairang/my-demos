package com.xsn.gc;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class TestRefrence {

    Node root = new Node();

    static class Basic {
        private static int[] _1MB = new int[1024 * 1024];
    }

    @AllArgsConstructor
    @NoArgsConstructor
    static class Node {
        private Basic basic;
        private Node next;
    }

    public static void main(String[] args) {
        TestRefrence testRefrence = new TestRefrence();

        Node a = new Node(new Basic(), null);
        Node b = new Node(new Basic(), a);
        testRefrence.root.basic = new Basic();
        testRefrence.root.next = b;

        System.out.println("11111");

        testRefrence.root.basic = null;
        testRefrence.root.next = null;
        testRefrence.root = null;
        System.gc();

        System.out.println("22222");
    }
}
