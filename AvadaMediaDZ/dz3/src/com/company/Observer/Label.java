package com.company.Observer;

public class Label implements Observer {

    String data;

    public Label(String data) {
        this.data = data;
    }

    @Override
    public void event(String someData) {
        System.out.println("Changed data in Lable to: " + someData);
    }

}
