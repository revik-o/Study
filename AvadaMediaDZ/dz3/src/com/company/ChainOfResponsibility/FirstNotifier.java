package com.company.ChainOfResponsibility;

public class FirstNotifier extends ChainOfResponsibility_Notifier {

    public FirstNotifier(int priority) {
        super(priority);
    }

    @Override
    public void print(String s) {
        System.out.println("First Notifier: " + s);
    }

}
