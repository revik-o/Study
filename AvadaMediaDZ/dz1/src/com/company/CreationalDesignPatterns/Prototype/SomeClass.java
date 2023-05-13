package com.company.CreationalDesignPatterns.Prototype;

public class SomeClass implements Copyable{

    int anInt;
    String string;

    public SomeClass(int anInt, String string){
        this.anInt = anInt;
        this.string = string;
    }

    @Override
    public Object copy() {
        SomeClass someClass = new SomeClass(this.anInt, this.string);
        return someClass;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "anInt=" + anInt +
                ", string='" + string + '\'' +
                '}';
    }
}
