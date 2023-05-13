package com.company.State;

public class Human {

    State state;

    public void setState(State state) {
        this.state = state;
    }

    public void doSomething() {
        state.doSomething();
        if (state instanceof RestState) {
            state = new Work();
        } else if (state instanceof Work) {
            state = new RestState();
        }
    }

}
