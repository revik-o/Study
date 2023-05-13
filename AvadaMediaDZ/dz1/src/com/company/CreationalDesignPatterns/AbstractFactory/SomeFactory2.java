package com.company.CreationalDesignPatterns.AbstractFactory;

public class SomeFactory2 implements SomeFactory{
    @Override
    public Object crate(String s) throws ClassNotFoundException {
        if (s.equals("SomeClass2")){
            return new SomeClass2();
        }else if (s.equals("SomeClass4")) {
            return new SomeClass4();
        }else {
            throw new ClassNotFoundException(s + "class not found");
        }
    }
}
