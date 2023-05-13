package com.company.CreationalDesignPatterns.Factory;

public class Cat implements Animal {
    @Override
    public void someSound() {
        System.out.println("meow");
    }
}