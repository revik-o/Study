package com.company.decorator;

public class SeniorJavaDev implements Decorator {

    public SeniorJavaDev(Developer developer) {
        super(developer);
    }

    public String makeCodeRev() {
        return "make code rev";
    }

    @Override
    public String makeJob() {
        return developer.makeJob() + " " + makeCodeRev();
    }
}
