package com.xsn.clone;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class Test {

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class Node {
        String a;
        String b;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class Node2 {
        Node a;
        Node b;
    }

    static void remove(Node node) {
        node = null;
    }

    public static void main(String[] args) {
        Node node = new Node("1", "1");
        remove(node);
        System.out.println(node);

        Node a = new Node("1", "1");
        Node b = new Node("2", "2");
        Node2 node2 = new Node2(a, b);
        remove(node2.a);
        System.out.println(node2);
    }
}
