package Other.SOLID.SingleResponsibilityPrinciple;

public class ConnectionManagement implements IConnectionManagement {
    @Override
    public void connect() {
        System.out.println("Device connected");
    }
}
