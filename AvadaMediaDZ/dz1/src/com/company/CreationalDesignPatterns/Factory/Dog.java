package com.company.CreationalDesignPatterns.Factory;

public class Dog implements Animal {
    @Override
    public void someSound() {
        System.out.println("bark");
    }
}