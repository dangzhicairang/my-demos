package com.xsn.rx;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

@Slf4j
@AllArgsConstructor
public class MyObserver implements Observer {

    public String name;

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof MyObservable) {
            System.out.println(name + " get the " + ((MyObservable) o).getName() + " subcribe something");
        }
    }
}
