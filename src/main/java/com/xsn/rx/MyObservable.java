package com.xsn.rx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;

@Data
@Slf4j
@AllArgsConstructor
public class MyObservable extends Observable {

    public String name;

    public void subscribe(String msg) {
        setChanged();
        notifyObservers("msg");
        clearChanged();
    }

}
