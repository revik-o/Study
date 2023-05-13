package com.company.CreationalDesignPatterns.AbstractFactory;

public class SomeFactory1 implements SomeFactory{
    @Override
    public Object crate(String s) throws ClassNotFoundException {
        if (s.equals("SomeClass1")){
            return new SomeClass1();
        }else if (s.equals("SomeClass3")) {
            return new SomeClass3();
        }else {
            throw new ClassNotFoundException(s + "class not found");
        }
    }
}
