package com.company.Mediator;

public class Person2 implements SomePerson {

    public String name;
    private INotify iNotify;

    public Person2(INotify iNotify, String name) {
        this.iNotify = iNotify;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void message(String s) {
        System.out.println(s);
    }
}
