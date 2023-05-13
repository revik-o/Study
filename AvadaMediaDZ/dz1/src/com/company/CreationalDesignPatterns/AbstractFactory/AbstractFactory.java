package com.company.CreationalDesignPatterns.AbstractFactory;

public class AbstractFactory {

    public SomeFactory createFactory(String s) throws ClassNotFoundException {
        if (s.equals("SomeFactory1")){
            return new SomeFactory1();
        }else if (s.equals("SomeFactory2")) {
            return new SomeFactory2();
        }else {
            throw new ClassNotFoundException(s + "class not found");
        }
    }

}