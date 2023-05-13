package com.company.ChainOfResponsibility;

public class SecondNotifier extends ChainOfResponsibility_Notifier {

    public SecondNotifier(int priority) {
        super(priority);
    }

    @Override
    public void print(String s) {
        System.out.println("Second Notifier: " + s);
    }

}
