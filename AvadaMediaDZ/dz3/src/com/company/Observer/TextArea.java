package com.company.Observer;

public class TextArea implements Observer {

    String data;

    public TextArea(String data) {
        this.data = data;
    }

    @Override
    public void event(String someData) {
        System.out.println("Changed data in TextArea to: " + someData);
    }

}
