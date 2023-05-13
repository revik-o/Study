package com.company.facade;

public class Facade {

    system system_ = new system();
    HDD hdd = new HDD();

    public void start() {
        system_.switchOn();
        hdd.startOS();
        hdd.closeOS();
        system_.switchOff();
    }

}
