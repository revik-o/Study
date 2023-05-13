package com.company.Memento;

public class CareTaker {

    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        if (this.memento == null)
            this.memento = memento;
        else {
            this.memento.setState(memento.getState());
        }
    }
}
