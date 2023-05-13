package com.company.Observer;

public interface IObserved {
    void add(Observer observer);
    void remove(Observer observer);
    void notifyAllObservers();
}
