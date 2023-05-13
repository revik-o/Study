package com.company;

import com.company.CreationalDesignPatterns.AbstractFactory.AbstractFactory;
import com.company.CreationalDesignPatterns.AbstractFactory.SomeFactory;
import com.company.CreationalDesignPatterns.AbstractFactory.SomeInterface1;
import com.company.CreationalDesignPatterns.AbstractFactory.SomeInterface2;
import com.company.CreationalDesignPatterns.Builder.DeveloperBuilder;
import com.company.CreationalDesignPatterns.Factory.Factory;
import com.company.CreationalDesignPatterns.Factory.Animal;
import com.company.CreationalDesignPatterns.Factory.Dog;
import com.company.CreationalDesignPatterns.Prototype.SomeClass;
import com.company.CreationalDesignPatterns.SingletonClass;

public class Main {

    public static void main(String[] args) {
        OOP();
        CreationalDesignPatterns();
    }

    static void OOP() {
        SomePerson person = new SomePerson("Ivan");
        System.out.println(person.getName());
        person.startWalking();
        person.stopWalking();

        SomePerson oleg = new Oleg();
        oleg.setAge((byte) 20);
        System.out.println(oleg.getAge());
        oleg.setAge("21");
        System.out.println(oleg.getAge());
        System.out.println(oleg.getName());

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                oleg.stopWalking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        oleg.startWalking();
    }

    static void CreationalDesignPatterns() {
        //Singleton
        System.out.println("SingletonClass.SOCKET_PORT: " + SingletonClass.SOCKET_PORT);
        //Factory
        System.out.println("\nFactory:");
        try {
            Animal cat = new Factory().animalFactory("cat");
            cat.someSound();
            Animal dog = Factory.ObjectFactoryCreate(Dog.class);
            dog.someSound();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Abstract Factory
        System.out.println("\nAbstract Factory:");
        try {
            AbstractFactory Factory = new AbstractFactory();
            SomeFactory someFactory1 = Factory.createFactory("SomeFactory1");
            SomeInterface1 someClass1 = (SomeInterface1) someFactory1.crate("SomeClass1");
            someClass1.someMethod();
            SomeFactory someFactory2 = Factory.createFactory("SomeFactory2");
            SomeInterface2 someClass4 = (SomeInterface2) someFactory2.crate("SomeClass4");
            someClass4.someMethod2();
        } catch (ClassNotFoundException e) { e.printStackTrace(); }
        //Builder
        System.out.println("\nBuilder:");
        DeveloperBuilder developerBuilder = new DeveloperBuilder().setName("Oleg").setAge(20).setYearsOfExperience(2);
        System.out.println(developerBuilder);
        //Prototype
        System.out.println("\nPrototype:");
        SomeClass someClass = new SomeClass(100, "Text1");
        System.out.println(someClass);
        SomeClass copy = (SomeClass) someClass.copy();
        System.out.println("Copy: " + copy);
    }

}

// OOP
class SomePerson {

    // encapsulation
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private byte age;

    public byte getAge() {
        return age;
    }

    //polymorphism
    public void setAge(byte age) {
        this.age = age;
    }

    public void setAge(String age) {
        try {
            this.age = Byte.parseByte(age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected int speed;
    protected int distance;
    protected boolean isWalking;

    public SomePerson(String name) {
        this.name = name;
    }

    public void startWalking() {
        System.out.println("Don't have realization");
    }

    public void stopWalking() {
        System.out.println("Don't have realization");
    }

}

//inheritance
class Oleg extends SomePerson {

    public Oleg() {
        super("Oleg");
    }

    {
        this.speed = 1;
        this.distance = 0;
    }

    @Override
    public void startWalking() {
        this.isWalking = true;
        System.out.println("Start walking");
        while (this.isWalking) {
            try {
                Thread.sleep(1000);
                this.distance += speed;
                System.out.println("Passed: " + this.distance + "m");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println("Stop walking");
    }

    @Override
    public void stopWalking() {
        this.isWalking = false;
    }

}