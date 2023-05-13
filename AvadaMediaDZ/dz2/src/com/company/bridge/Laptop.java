package com.company.bridge;

public class Laptop extends Device {

    Laptop(Manufacturer manufacturer) {
        super(manufacturer);
    }

    @Override
    String getName() {
        return "Laptop";
    }

}
