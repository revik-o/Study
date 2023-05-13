package com.company.Memento;

public class Originator {


    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void getDataFromMemento(Memento memento) {
        this.state = memento.getState();
    }

    public Memento getMemento() {
        return new Memento().setState(state);
    }

}
