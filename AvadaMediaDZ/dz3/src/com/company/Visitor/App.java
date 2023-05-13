package com.company.Visitor;

public class App implements IProject {
    @Override
    public void doJob(IDev iDev) {
        iDev.create(this);
    }
}
