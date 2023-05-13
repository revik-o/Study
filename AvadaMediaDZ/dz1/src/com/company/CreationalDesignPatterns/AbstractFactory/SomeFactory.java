package com.company.CreationalDesignPatterns.AbstractFactory;

public interface SomeFactory {
    Object crate(String s) throws ClassNotFoundException;
}
