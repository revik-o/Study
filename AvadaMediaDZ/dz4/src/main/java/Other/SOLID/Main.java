package Other.SOLID;

import Other.SOLID.DependencyInversionPrinciple.Brand;
import Other.SOLID.DependencyInversionPrinciple.Device;
import Other.SOLID.InterfaceSegregationPrinciple.IArea;
import Other.SOLID.InterfaceSegregationPrinciple.Rectangle1;
import Other.SOLID.LiskovSubstitutionPrinciple.IShape;
import Other.SOLID.LiskovSubstitutionPrinciple.Rectangle;
import Other.SOLID.LiskovSubstitutionPrinciple.Square;
import Other.SOLID.OpenClosedPrinciple.Client;
import Other.SOLID.OpenClosedPrinciple.Server;
import Other.SOLID.SingleResponsibilityPrinciple.ConnectionManagement;
import Other.SOLID.SingleResponsibilityPrinciple.DataTransfer;
import Other.SOLID.SingleResponsibilityPrinciple.Router;

public class Main {

    public static void main(String[] args) {
        //Single Responsibility Principle
        System.out.println("=======================================================\nSingle Responsibility Principle");
        Router router = new Router(new ConnectionManagement(), new DataTransfer());
        router.getConnectionManagement().connect();
        router.getDataTransfer().acceptData();
        router.getDataTransfer().sendData();
        //Open Closed Principle
        System.out.println("\nOpen Closed Principle");
        Client client = new Client(new Server(), "LocalClient");
        client.connect();
        //Liskov Substitution Principle
        System.out.println("\nLiskov Substitution Principle");
        IShape shape = new Square(2);
        System.out.println(shape.getArea());
        shape = new Rectangle(2, 4);
        System.out.println(shape.getArea());
        //Interface Segregation Principle
        System.out.println("\nInterface Segregation Principle");
        IArea area = new Rectangle1(5, 10);
        System.out.println(area.getArea());
        //Dependency Inversion Principle
        System.out.println("\nDependency Inversion Principle");
        Device device = new Device("Some Device Model", new Brand());
        device.showInfo();
        System.out.println("\n=======================================================");
    }

}
