package com.xsn.rx;

import java.util.Observable;

public class Test {

    public static void main(String[] args) {
        Observable obs = new MyObservable("ddobs");
        obs .addObserver(new MyObserver("ddob1"));
        obs .addObserver(new MyObserver("ddob2"));
        ((MyObservable) obs).subscribe("msg");
    }
}
