package com.company.adapter;

public class AdaptertForSomeJavaAppToSomeDB implements SomeDB {

    SomeJavaApp javaApp;

    public AdaptertForSomeJavaAppToSomeDB(SomeJavaApp javaApp) {
        this.javaApp = javaApp;
    }


    @Override
    public void select() {
        javaApp.selectObject();
    }

    @Override
    public void insert() {
        javaApp.insertObject();
    }

    @Override
    public void update() {
        javaApp.updateObject();
    }

    @Override
    public void delete() {
        javaApp.deleteObject();
    }
}
