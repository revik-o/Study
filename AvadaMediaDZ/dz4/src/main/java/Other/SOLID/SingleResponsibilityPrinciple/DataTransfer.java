package Other.SOLID.SingleResponsibilityPrinciple;

public class DataTransfer implements IDataTransfer {

    @Override
    public void sendData() {
        System.out.println("Start send data");
    }

    @Override
    public void acceptData() {
        System.out.println("Start accept data");
    }
}
