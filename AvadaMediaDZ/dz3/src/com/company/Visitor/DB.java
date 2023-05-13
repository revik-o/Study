package com.company.Visitor;

public class DB implements IProject {
    @Override
    public void doJob(IDev iDev) {
        iDev.create(this);
    }
}
