package com.company.bridge;

public abstract class Device {

    Manufacturer manufacturer;

    public Device(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    abstract String getName();

    public void showDetails() {
        manufacturer.show();
        System.out.println(getName());
    }

}
