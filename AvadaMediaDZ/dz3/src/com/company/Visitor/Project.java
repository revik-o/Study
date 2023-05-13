package com.company.Visitor;

public class Project implements IProject {

    private IProject[] iProjects = {new App(), new DB()};

    @Override
    public void doJob(IDev iDev) {
        for (IProject project: iProjects) {
            project.doJob(iDev);
        }
    }
}
