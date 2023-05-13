package com.company;

import com.company.ChainOfResponsibility.*;
import com.company.Command.*;
import com.company.Interpreter.*;
import com.company.Iterator.*;
import com.company.Mediator.*;
import com.company.Memento.*;
import com.company.Observer.Label;
import com.company.Observer.Observed;
import com.company.Observer.Observer;
import com.company.Observer.TextArea;
import com.company.State.*;
import com.company.Strategy.*;
import com.company.TemplateMethod.*;
import com.company.Visitor.*;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Template Method -? >
        System.out.println("Template Method:");
        Template page1 = new Page1();
        Template page2 = new Page2();
        page1.doSmth();
        System.out.println("=========");
        page2.doSmth();
        //Mediator
        System.out.println("\nMediator:");
        Mediator mediator = new Mediator();
        Person1 person1 = new Person1(mediator, "Oleg");
        Person2 person2 = new Person2(mediator, "Vova");
        mediator.addPerson(person1);
        mediator.addPerson(person2);
        mediator.notifyAll("Hi");
        //Chain of Responsibility
        System.out.println("\nChain of Responsibility:");
        FirstNotifier firstNotifier = new FirstNotifier(Priority.first);
        SecondNotifier secondNotifier = new SecondNotifier(Priority.second);
        firstNotifier.setChainOfResponsibility_notifier(secondNotifier);
        ThirdNotifier thirdNotifier = new ThirdNotifier(Priority.third);
        secondNotifier.setChainOfResponsibility_notifier(thirdNotifier);
        firstNotifier.sendMessage("text 1", Priority.first);
        firstNotifier.sendMessage("text 2", Priority.second);
        firstNotifier.sendMessage("text 3", Priority.third);
        //Observer -? >
        System.out.println("\nObserver:");
            //Like View
            TextArea textArea = new TextArea("TextArea text");
            Label label = new Label("Label text");
            Observed observed = new Observed();
            observed.add(textArea);
            observed.add(label);
            //Like ViewModel
            ViewModel viewModel = new ViewModel();
        //Strategy
        System.out.println("\nStrategy:");
        int[] arr = new int[5];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(100);
        StrategyClient strategyClient = new StrategyClient(new QuickSort());
        strategyClient.sortArr(arr);
        for (int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(100);
        strategyClient = new StrategyClient(new BubbleSort());
        strategyClient.sortArr(arr);
        //Command
        System.out.println("\nCommand:");
        DataBase dataBase = new DataBase();
        Client client = new Client(new SelectCommand(dataBase), new InsertCommand(dataBase), new UpdateCommand(dataBase), new DeleteCommand(dataBase));
        client.selectFromDB();
        client.insertToDB();
        //State
        System.out.println("\nState:");
        RestState state = new RestState();
        Human human = new Human();
        human.setState(state);
        for (int i = 0; i < 4; i++)
            human.doSomething();
        //Visitor -? >
        System.out.println("\nVisitor:");
        Project project = new Project();
        project.doJob(new JavaDev());
        System.out.println("=========");
        project.doJob(new CppDev());
        //Interpreter
        System.out.println("\nInterpreter:");
        Evaluate expression = new Evaluate("7+3+5");
        System.out.println(expression.interpreter(expression));
        //Iterator
        System.out.println("\nIterator:");
        OS os = new OS();
        Iterator iterator = os.getIterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        //Memento -? >
        System.out.println("\nMemento:");
        Originator originator = new Originator();
        originator.setState("St 1");
        System.out.println(originator.getState());
        CareTaker careTaker = new CareTaker();
        careTaker.setMemento(originator.getMemento());
        originator.setState("St 2");
        careTaker.setMemento(originator.getMemento());
        originator.setState("St 3");
        System.out.println(originator.getState());
        originator.getDataFromMemento(careTaker.getMemento());
        System.out.println(originator.getState());
        originator.getDataFromMemento(careTaker.getMemento());
        System.out.println(originator.getState());
    }
}

final class ViewModel {

    Observed observed = new Observed();

    public ViewModel() {
        observed.setSomeData("Some data 1");
        System.out.println("===============================");
        observed.setSomeData("Some data 2");
    }

}