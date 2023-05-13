package com.company.ChainOfResponsibility;

public abstract class ChainOfResponsibility_Notifier {

    int priority;
    ChainOfResponsibility_Notifier chainOfResponsibility_notifier;

    public void setChainOfResponsibility_notifier(ChainOfResponsibility_Notifier chainOfResponsibility_notifier) {
        this.chainOfResponsibility_notifier = chainOfResponsibility_notifier;
    }

    public ChainOfResponsibility_Notifier(int priority){
        this.priority = priority;
    }

    public void sendMessage(String s, int priority) {
        if (priority <= this.priority)
            print(s);
        if (this.chainOfResponsibility_notifier != null)
            this.chainOfResponsibility_notifier.sendMessage(s, priority);
    }

    public abstract void print(String s);

}
