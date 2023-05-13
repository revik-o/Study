package com.company.ChainOfResponsibility;

public class ThirdNotifier extends ChainOfResponsibility_Notifier {

    public ThirdNotifier(int priority) {
        super(priority);
    }

    @Override
    public void print(String s) {
        System.out.println("Third Notifier: " + s);
    }

}
