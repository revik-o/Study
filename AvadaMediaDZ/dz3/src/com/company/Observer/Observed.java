package com.company.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observed implements IObserved {

    private String someData;

    private static List<Observer> elem = new ArrayList<>();

    public void setSomeData(String someData) {
        this.someData = someData;
        notifyAllObservers();
    }

    public String getSomeData() {
        return someData;
    }

    @Override
    public void add(Observer observer) {
        this.elem.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        this.elem.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer observer: elem)
            observer.event(this.getSomeData());
    }

}