package com.company.bridge;

public class Smartphone extends Device {

    public Smartphone(Manufacturer manufacturer) {
        super(manufacturer);
    }

    @Override
    String getName() {
        return "Smartphone";
    }

}
