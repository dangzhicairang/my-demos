package com.xsn.container;

public interface Linked<T> {

    T addFirst(T t);

    T addLast(T t);

    T add(T t, int index);

    T removeFirst();

    T removeLast();

    T remove(T t);

    boolean contain(T t);

    boolean isEmpty();

    int getSize();
}
