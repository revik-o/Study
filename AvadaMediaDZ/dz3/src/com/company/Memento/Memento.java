package com.company.Memento;

import java.util.Stack;

public class Memento {

    private Stack<String> state = new Stack<>();

    public Memento setState(String strState) {
        this.state.add(strState);
        return this;
    }

    public String getState() {
        return state.pop();
    }

}