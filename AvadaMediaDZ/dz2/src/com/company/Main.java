package com.company;

import com.company.adapter.AdaptertForSomeJavaAppToSomeDB;
import com.company.adapter.JavaApp;
import com.company.adapter.SomeDB;
import com.company.adapter.SomeJavaApp;
import com.company.bridge.Device;
import com.company.bridge.Smartphone;
import com.company.bridge.Sony;
import com.company.composite.CompositeShape;
import com.company.composite.Square;
import com.company.composite.Triangle;
import com.company.decorator.Developer;
import com.company.decorator.JavaDev;
import com.company.decorator.SeniorJavaDev;
import com.company.facade.Facade;
import com.company.flyweight.FlyweightDomainName;
import com.company.proxy.IPage;
import com.company.proxy.Page;
import com.company.proxy.ProxyPage;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Adapter
        System.out.println("Adapter:");
        SomeJavaApp javaApp = new JavaApp();
        javaApp.selectObject();
        SomeDB db = new AdaptertForSomeJavaAppToSomeDB(javaApp);
        db.select();

        // Composite
        System.out.println("\nComposite:");
        Square square1 = new Square();
        Square square2 = new Square();
        Triangle triangle = new Triangle();
        CompositeShape compositeShape = new CompositeShape();
        compositeShape.addShape(square1);
        compositeShape.addShape(square2);
        compositeShape.addShape(triangle);
        compositeShape.create();
        compositeShape.removeShape(triangle);
        compositeShape.create();

        // Proxy
        System.out.println("\nProxy:");
        String url = "Google.com";
        IPage page = new Page(url);
        page = new ProxyPage(url);
        page.show();

        // Flyweight
        System.out.println("\nFlyweight:");
        FlyweightDomainName flyweightDomainName = new FlyweightDomainName();
        System.out.println(flyweightDomainName.getDomainNameByName("Google.com").name);
        System.out.println(flyweightDomainName.getDomainNameByName("Amazon.com").name);
        System.out.println(flyweightDomainName.getDomainNameByName("Google.com").name);

        // Facade
        System.out.println("\nFacade:");
        Facade facade = new Facade();
        facade.start();

        // Bridge
        System.out.println("\nBridge:");
        Device device = new Smartphone(new Sony());
        device.showDetails();

        // Decorator
        System.out.println("\nDecorator:");
        Developer developer = new SeniorJavaDev(new JavaDev());
        System.out.println(developer.makeJob());
    }

}