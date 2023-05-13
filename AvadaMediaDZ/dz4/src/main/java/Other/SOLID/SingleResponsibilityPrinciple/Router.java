package Other.SOLID.SingleResponsibilityPrinciple;

public class Router {

    private final IConnectionManagement iConnectionManagement;
    private final IDataTransfer iDataTransfer;

    public Router(IConnectionManagement connectionManagement, IDataTransfer dataTransfer) {
        this.iConnectionManagement = connectionManagement;
        this.iDataTransfer = dataTransfer;
    }

    public IConnectionManagement getConnectionManagement() {
        return iConnectionManagement;
    }

    public IDataTransfer getDataTransfer() {
        return iDataTransfer;
    }
}

class r {

    public void connect() {
        System.out.println("Device connected");
    }

    public void sendData() {
        System.out.println("Start send data");
    }

    public void acceptData() {
        System.out.println("Start accept data");
    }

}
