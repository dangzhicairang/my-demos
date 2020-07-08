package com.xsn.container;

import lombok.Data;

@Data
public class MyLinked<T> implements Linked<T> {

    private int size;
    private Node<T> head;

    public MyLinked() {
        size = 0;
        head = null;
    }

    private class Node<T> {
        Node<T> next;
        T data;

        Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }

        Node(T data) {
            this(null, data);
        }

        @Override
        public String toString() {
            return data + "----->" + next;
        }
    }

    @Override
    public T addFirst(T t) {
        head = new Node(head, t);
        ++ size;
        return t;
    }

    @Override
    public T addLast(T t) {
        add(t, size);
        return t;
    }

    @Override
    public T add(T t, int index) {

        if (index < 0 || index > size) {
            throw new RuntimeException("error index");
        }

        if (index == 0) {
            return addFirst(t);
        }

        Node node = new Node(t);
        Node pre = head;

        for (int i = 0; i < index - 1; i++) {
            pre = pre.next;
        }

        node.next = pre.next;
        pre.next = node;
        ++ size;

        return t;
    }

    @Override
    public T removeFirst() {

        if (head == null) {
            throw new RuntimeException("already empty");
        }

        T t = head.data;
        head = head.next;
        -- size;
        return t;
    }

    @Override
    public T removeLast() {
        if (head == null) {
            throw new RuntimeException("already empty");
        }

        Node node = head;
        Node<T> next = head;
        while (next.next != null) {
            node = next;
            next = next.next;
        }

        T t = next.data;
        node.next = null;
        -- size;

        return t;
    }

    @Override
    public T remove(T t) {
        if (head == null) {
            throw new RuntimeException("already empty");
        }

        if (t.equals(head.data)) {
            return removeFirst();
        }

        Node pre = head;
        Node node = head;
        while (node != null && !t.equals(node.data)) {
            pre = node;
            node = pre.next;
        }

        if (node != null) {
            pre.next = node.next;
            -- size;
            return t;
        }

        throw new RuntimeException("no such element " + t);
    }

    @Override
    public boolean contain(T t) {
        Node node = head;

        while (node != null && !t.equals(node.data)) {
            node = node.next;
        }

        return node != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {

        return head.toString();
    }

    public static void main(String[] args) {
        MyLinked<String> singleLinked = new MyLinked<>();

        System.out.println(singleLinked.add("1", 0));
        System.out.println(singleLinked.add("2", 1));
        System.out.println(singleLinked.addFirst("3"));
        System.out.println(singleLinked.addFirst("4"));
        System.out.println(singleLinked.addLast("5"));
        System.out.println(singleLinked.addLast("6"));
        System.out.println(singleLinked);

        System.out.println("==========================");

        System.out.println(singleLinked.removeFirst());
        System.out.println(singleLinked.removeLast());
        System.out.println(singleLinked.remove("1"));
        System.out.println(singleLinked);
        System.out.println(singleLinked.contain("5"));
        System.out.println("==========================");

        /*System.out.println(singleLinked.size);
        System.out.println(singleLinked.contain("1"));
        System.out.println(singleLinked.isEmpty());
        System.out.println(singleLinked.getHead());

        System.out.println("=========================");

        System.out.println(singleLinked.remove("1"));
        System.out.println(singleLinked.size);
        System.out.println(singleLinked.contain("1"));
        System.out.println(singleLinked.isEmpty());
        System.out.println(singleLinked.getHead());
        System.out.println(singleLinked);*/

    }
}
