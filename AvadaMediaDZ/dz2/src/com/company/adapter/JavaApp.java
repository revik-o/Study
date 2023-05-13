package com.company.adapter;

public class JavaApp implements SomeJavaApp{
    @Override
    public void selectObject() {
        System.out.println("Select object");
    }

    @Override
    public void insertObject() {
        System.out.println("Insert object");
    }

    @Override
    public void updateObject() {
        System.out.println("Update object");
    }

    @Override
    public void deleteObject() {
        System.out.println("Delete object");
    }
}
