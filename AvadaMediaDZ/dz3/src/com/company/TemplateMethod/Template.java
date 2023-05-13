package com.company.TemplateMethod;

public abstract class Template {

    public void doSmth() {
        System.out.println("Start");
        mid();
        System.out.println("End");
    }

    protected abstract void mid();

}
