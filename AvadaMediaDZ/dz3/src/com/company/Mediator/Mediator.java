package com.company.Mediator;

import java.util.ArrayList;

public class Mediator implements INotify {

    private ArrayList<SomePerson> arrayList = new ArrayList<>();

    public void addPerson(SomePerson somePerson) {
        arrayList.add(somePerson);
    }

    public void removePerson(SomePerson somePerson) {
        arrayList.remove(somePerson);
    }

    @Override
    public void notifyAll(String s) {
        for (SomePerson somePerson: arrayList) {
            somePerson.message("Notify for " + somePerson.getName() + ": " + s);
        }
    }

}
